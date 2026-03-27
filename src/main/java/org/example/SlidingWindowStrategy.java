package org.example;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentSkipListSet;

public class SlidingWindowStrategy implements RateLimittingStrategy{
    private final ConcurrentSkipListSet<LocalDateTime> set = new ConcurrentSkipListSet<>();
    private int x;
    private int t;
    private final RemoteResource rr;
    public SlidingWindowStrategy(int x,int t,RemoteResource rr){
        this.x = x;
        this.t = t;
        this.rr = rr;
    }
    @Override
    public void limit() {
        LocalDateTime curr = LocalDateTime.now();
        LocalDateTime windowStart = curr.minusSeconds(t);
        set.removeIf(timestamp -> timestamp.isBefore(windowStart));
        if(set.size() >= x) {
            System.out.println("429. Too Many Requests");
            return;
        }
        set.add(curr);
        rr.getResponse();
    }
}
