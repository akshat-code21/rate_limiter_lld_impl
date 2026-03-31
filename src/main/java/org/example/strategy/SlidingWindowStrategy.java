package org.example.strategy;

import org.example.resource.IResource;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SlidingWindowStrategy implements RateLimittingStrategy {
    private final ConcurrentHashMap<Long, Instant> requests = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(0);
    private final int maxRequests;
    private final int windowLength;

    public SlidingWindowStrategy(int maxRequests, int windowLength) {
        this.maxRequests = maxRequests;
        this.windowLength = windowLength;
    }

    @Override
    public boolean limit() {
        boolean allowed;
        synchronized (this) {
            Instant now = Instant.now();
            Instant windowStart = now.minusSeconds(windowLength);
            requests.values().removeIf(t -> t.isBefore(windowStart));
            allowed = requests.size() < maxRequests;
            if (allowed) {
                requests.put(counter.incrementAndGet(), now);
            } else {
                System.out.println("429 Too Many Requests");
            }
        }

        return allowed;
    }
}
