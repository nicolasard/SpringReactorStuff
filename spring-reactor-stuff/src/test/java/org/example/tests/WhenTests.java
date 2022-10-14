package org.example.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class WhenTests {


    @Test
    public void testWhen(){
        Mono<String> streamA = Mono.just("streamA");
        Mono<String> streamB = Mono.just("streamB");
        streamA.when(streamB).log().subscribe();
    }
}
