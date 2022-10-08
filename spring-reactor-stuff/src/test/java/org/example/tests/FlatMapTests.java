package org.example.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FlatMapTests {


    @Test
    public void testBuffer(){
        Flux<String> dataStream = Flux.just("Java", "C++", "Python", "Ruby");
        dataStream
                .flatMap(t-> this.printMethod("1: ",t))
                //.flatMap(Flux::fromIterable)
                .flatMap(t-> this.printMethod("2: ",t))
                .subscribe();

    }

    public Flux<String> printMethod(final String prefix, final String s){
        try{
            final Integer randomeTime = (int) ((Math.random() * (400 - 50)) + 50);
            Thread.sleep(randomeTime); //This is to put some entropy in the "print method process"
        }catch(InterruptedException e){
            System.out.println("Something happened trying to sleep");
        }
        Thread t = Thread.currentThread();
        System.out.println("(Thread name=" + t.getName() + ") -  " + prefix+s);
        return Flux.just(s);
    }
}
