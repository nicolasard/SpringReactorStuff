package org.example.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    private static final Logger logger = LogManager.getLogger("HelloWorldController");

    @GetMapping("/{id}")
    private Mono<String> getEmployeeById(@PathVariable String id) {
        logger.info("In the controller");
        Flux<String> dataStream = Flux.just("Java", "C++", "Python", "Ruby");
        dataStream
                .map(t-> this.printMethod("1: ",t))
                .buffer(3)
                .flatMap(Flux::fromIterable)
                .map(t-> this.printMethod("2: ",t))
                .subscribe();
        return Mono.just(id);
    }

    public String printMethod(final String prefix, final String s){
        try{
            final Integer randomeTime = (int) ((Math.random() * (400 - 50)) + 50);
            Thread.sleep(randomeTime); //This is to put some entropy in the "print method process"
        }catch(InterruptedException e){
            logger.info("Something happened trying to sleep");
        }
        logger.info(prefix+s);
        return s;
    }
}