package org.example.proxy;

import org.example.strategy.RateLimittingStrategy;
import org.example.resource.IResource;

public class RemoteResourceProxy implements IResource {
    private final RateLimittingStrategy rateLimittingStrategy;
    private final IResource remoteResource;

    public RemoteResourceProxy(RateLimittingStrategy rateLimittingStrategy, IResource remoteResource) {
        this.rateLimittingStrategy = rateLimittingStrategy;
        this.remoteResource = remoteResource;
    }

    @Override
    public void getResponse() {
        if (rateLimittingStrategy.limit()) {
            remoteResource.getResponse();
        }
    }
}
