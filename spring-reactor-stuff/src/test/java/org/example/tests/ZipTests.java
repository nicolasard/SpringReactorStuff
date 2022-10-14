package org.example.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ZipTests {


    @Test
    public void testZipWith(){
        Mono<String> streamA = Mono.just("streamA");
        Mono<String> streamB = Mono.just("streamB");
        streamA.zipWith(streamB).log().subscribe();
    }

    @Test
    public void testZipWithandFlux(){
        Flux<String> streamA = Flux.just("streamA","streamA","streamA","streamA").map(t->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return t;});
        Flux<String> streamB = Flux.just("streamB","streamB");
        streamA.collectList().zipWith(streamB.collectList()).log().subscribe();
    }

    @Test
    public void testZipWhen(){
        Mono<String> streamA = Mono.just("streamA");
        Mono<String> streamB = Mono.just("streamB");
        streamA.zipWhen(t->streamB).log().subscribe();
    }

    @Test
    public void testConvertMonoToFlux(){
        Mono<String> streamA = Mono.just("streamA");
        Flux<String> streamB = Flux.concat(streamA);
        streamB.log().subscribe();
    }
}
