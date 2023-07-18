package com.rh.api;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("counter")
public class CounterResource {
    private int count = 0;
    private final MeterRegistry registry;

    CounterResource(MeterRegistry registry) {
        this.registry = registry;
    }

    @GET
    public int counter() {
        registry.counter("api.call.number", "name", "counter").increment();
        return ++count;
    }
}

