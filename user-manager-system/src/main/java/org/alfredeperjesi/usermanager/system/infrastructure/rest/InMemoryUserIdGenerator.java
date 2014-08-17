package org.alfredeperjesi.usermanager.system.infrastructure.rest;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InMemoryUserIdGenerator implements UserIdGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public Integer generate() {
        return atomicInteger.incrementAndGet();
    }
}
