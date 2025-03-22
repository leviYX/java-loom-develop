package com.xy.demo05;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 补偿机制
 */
public class Pin2Demo {

    static {
       System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
       System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");// 256
    }

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("run" + Thread.currentThread().getName());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        //
                    }
                }
            }
        };
        ThreadFactory factory = Thread.ofVirtual().name("vt-", 1).factory();
        try (var exector = Executors.newThreadPerTaskExecutor(factory)){
            for (int i = 0; i < 15; i++) {
                exector.submit(task);
            }
        }
    }
}
