package org.alfredeperjesi.usermanager.system.infrastructure.rest;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class InMemoryUserIdGenerator implements UserIdGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public Integer generate() {
        return atomicInteger.incrementAndGet();
    }
}
