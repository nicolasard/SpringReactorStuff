package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class HelloWorldWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldWebFluxApplication.class, args);
    }
}