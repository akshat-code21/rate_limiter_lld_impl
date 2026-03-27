package org.example.proxy;

import org.example.strategy.RateLimittingStrategy;
import org.example.resource.IResource;

public class RemoteResourceProxy implements IResource {
    private final RateLimittingStrategy rateLimittingStrategy;
    public RemoteResourceProxy(RateLimittingStrategy rateLimittingStrategy){
        this.rateLimittingStrategy = rateLimittingStrategy;
    }
    @Override
    public void getResponse() {
        rateLimittingStrategy.limit();
    }
}
