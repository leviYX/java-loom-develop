package com.xy.demo03;

import com.xy.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * cputask demo 没有比平台线程每秒执行的指令更多，不会更快，调度方式和平台线程不同，抢占式和协作式
 * 平台线程 5：6.1  10：7.4   15:12.5
 * 虚拟线程 5：6.1  10：7.7   15:8
 * 虚拟线程在cpu密集任务中是比平台线程跑的更快的。 不对,cpu密集吞吐一样
 *
 */
public class CputaskDemo {
    private static final Logger log = LoggerFactory.getLogger(CputaskDemo.class);
    private static final int MAX_TASK_NUM = Runtime.getRuntime().availableProcessors() + 10;
//    static {
//        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
//        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
//    }
    public static void main(String[] args) {
        test(Thread.ofPlatform());// test pt
        test(Thread.ofVirtual());// test vt
    }

    public static void test(Thread.Builder builder) {
        var latch = new CountDownLatch(MAX_TASK_NUM);
        for (int i = 0; i < MAX_TASK_NUM; i++) {
            builder.start(()->{
                var begin = Instant.now();
                findFib(46);
                var end = Instant.now();
                log.info("cpu task ending,time cost is: {}", Duration.between(begin, end).toMillis());
                latch.countDown();
            });
        }
        try {
            latch.await();
        }catch (InterruptedException e) {
            throw new RuntimeException("interrupt exception");
        }
    }

    private static long findFib(long input) {
        return input < 2 ? input : findFib(input - 1) + findFib(input - 2);
    }

}
