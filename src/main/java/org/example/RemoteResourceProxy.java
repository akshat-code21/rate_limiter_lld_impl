package org.example;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentSkipListSet;

public class RemoteResourceProxy implements IResource{
    private final RateLimittingStrategy rateLimittingStrategy;
    public RemoteResourceProxy(RateLimittingStrategy rateLimittingStrategy){
        this.rateLimittingStrategy = rateLimittingStrategy;
    }
    @Override
    public void getResponse() {
        rateLimittingStrategy.limit();
    }
}
