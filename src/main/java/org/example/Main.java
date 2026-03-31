package org.example;

import org.example.proxy.RemoteResourceProxy;
import org.example.resource.IResource;
import org.example.resource.RemoteResource;
import org.example.strategy.RateLimittingStrategy;
import org.example.strategy.SlidingWindowStrategy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        IResource resource = new RemoteResource();
        RateLimittingStrategy strategy = new SlidingWindowStrategy(3, 4);
        RemoteResourceProxy proxy = new RemoteResourceProxy(strategy, resource);

        for (int i = 0; i < 10; i++) {
            proxy.getResponse();
            Thread.sleep(1000);
        }
    }
}