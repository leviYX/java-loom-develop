package com.xy.demo05;

import com.xy.demo04.NoBlockingDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SyncDemo {

    private static final Logger log = LoggerFactory.getLogger(SyncDemo.class);

    private static final List<Integer> list = new ArrayList<>();

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        var builder = Thread.ofVirtual();
        builder.name("vt-",1);
        demo01(builder);
    }

    private static void demo01(Thread.Builder builder) {
        var latch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            int j = i;
            builder.start(()->{
                add(j);
                latch.countDown();
            });
        }
        try {
            latch.await();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
       log.info("{}", list.size());
    }

    private synchronized static void add(int j){
        list.add(j);
    }

}
