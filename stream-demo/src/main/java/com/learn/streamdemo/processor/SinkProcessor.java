package com.learn.streamdemo.processor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkProcessor<T> {

    private final Sinks.Many<T> sink;

    public SinkProcessor() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    public void add(T t) {
        this.sink.tryEmitNext(t);
    }

    public Flux<T> flux() {
        return this.sink.asFlux();
    }
}
