package org.example.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;


public class HelloWorldTest {

    /*
     * Just print a flux value
     */
    @Test
    public void testHelloWorld(){
        Flux<String> dataStream = Flux.just("Java", "C++", "Python", "Ruby");
        dataStream.subscribe(prog ->
        {
            System.out.println("Hello Word "+prog);
        });
    }

    /*
     * Made a Buffer of the flux values you can comment the .buffer and see the difference.
     */
    @Test
    public void testBuffer(){
        Flux<String> dataStream = Flux.just("Java", "C++", "Python", "Ruby");
        dataStream
                .map(t-> this.printMethod("1: ",t))
                .buffer()
                .flatMap(Flux::fromIterable)
                .map(t-> this.printMethod("2: ",t))
                .subscribe();

    }

    /*
     * Made a Buffer of the flux values you can comment the .buffer and see the difference.
     */
    @Test
    public void testBufferWithMaxSize(){
        Flux<String> dataStream = Flux.just("Java", "C++", "Python", "Ruby");
        dataStream
                .map(t-> this.printMethod("1: ",t))
                .buffer(3)
                .flatMap(Flux::fromIterable)
                .map(t-> this.printMethod("2: ",t))
                .subscribe();

    }

    /*
     * Handle exception
     */
    @Test
    public void testOnError(){
        //TODO: not completed
        Flux<String> dataStream = Flux.just("Java", "C++", "Python", "Ruby");
        dataStream
                .map(t-> this.printMethod("1: ",t))
                .buffer(3)
                .flatMap(Flux::fromIterable)
                .map(t-> this.printMethod("2: ",t))
                .doOnError(Exception.class,t->printMethod("4",t.getMessage()))
                .subscribe();
    }

    public String printMethod(final String prefix, final String s){
        try{
            final Integer randomeTime = (int) ((Math.random() * (400 - 50)) + 50);
            Thread.sleep(randomeTime); //This is to put some entropy in the "print method process"
        }catch(InterruptedException e){
            System.out.println("Something happened trying to sleep");
        }
        Thread t = Thread.currentThread();
        System.out.println("(Thread name=" + t.getName() + ") -  " + prefix+s);
        return s;
    }
}
