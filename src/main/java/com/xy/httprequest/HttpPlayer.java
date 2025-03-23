package com.xy.httprequest;


import java.util.concurrent.Executors;

public class HttpPlayer {

    private static final int NUM_USER = 10;

    public static void main(String[] args) {
        var factory = Thread.ofVirtual().name("http-request-vt-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(factory)){
            for (int i = 0; i < NUM_USER; i++) {
                executor.submit(new DbRequestHandler(i));
                executor.submit(new RestRequestHandler(i));
            }
        }
    }
}
