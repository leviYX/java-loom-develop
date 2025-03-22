package com.xy.demo05;

import com.xy.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * pin 模型退化
 */
public class SyncPinDemo {


    private static final Logger log = LoggerFactory.getLogger(SyncPinDemo.class);

    private static final List<Integer> list = new ArrayList<>();

    private static final int THREAD_COUNT = 10;

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
                log.info("Task start,thread is {}", Thread.currentThread());
                add(j);
                log.info("Task end,thread is {}", Thread.currentThread());
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
        CommonUtils.sleep(Duration.ofSeconds(1));
        list.add(j);
    }

}
