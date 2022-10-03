package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class HelloWorldWebFluxApplication {

    public static void main(String[] args) {
        Flux<String> dataStream = Flux.just("Java", "C++", "Python", "Ruby");

        SpringApplication.run(HelloWorldWebFluxApplication.class, args);

        dataStream.subscribe(prog ->
        {
            System.out.println("Hello Word "+prog);

        });

    }
}