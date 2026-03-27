package org.example;

public class Main {
    public static void main(String[] args) {
        RemoteResourceProxy rrp = new RemoteResourceProxy(new SlidingWindowStrategy(3,4,new RemoteResource()));
        int i =0;
        while(i < 10){
            i++;
            try {
                rrp.getResponse();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}