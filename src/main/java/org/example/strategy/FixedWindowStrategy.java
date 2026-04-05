package org.example.strategy;

import java.time.Instant;

public class FixedWindowStrategy implements RateLimittingStrategy {
    private final int maxRequests;
    private final int windowLength;
    private int requestCount;
    private Instant windowStart;

    public FixedWindowStrategy(int maxRequests, int windowLength) {
        this.maxRequests = maxRequests;
        this.windowLength = windowLength;
        this.requestCount = 0;
        this.windowStart = Instant.now();
    }

    @Override
    public boolean limit() {
        synchronized (this) {
            Instant now = Instant.now();
            Instant windowEnd = windowStart.plusSeconds(windowLength);

            if (now.isAfter(windowEnd) || now.equals(windowEnd)) {
                windowStart = now;
                requestCount = 0;
            }

            if (requestCount < maxRequests) {
                requestCount++;
                return true;
            } else {
                System.out.println("429 Too Many Requests");
                return false;
            }
        }
    }
}
