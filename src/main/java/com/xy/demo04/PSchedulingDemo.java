package com.xy.demo04;

import com.xy.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * 调度式的，非抢占的
 * 但是可以通过yeild()方法让出调度
 */
public class PSchedulingDemo {

    private static final Logger log = LoggerFactory.getLogger(PSchedulingDemo.class);


    public static void main(String[] args) {
        var builder = Thread.ofPlatform();
        var pt1 = builder.unstarted(() -> longTimeTask(1));
        var pt2 = builder.unstarted(() -> shortTimeTask(2));
        pt1.start();
        pt2.start();

        // 阻塞主线程
        CommonUtils.sleep(Duration.ofSeconds(5));
    }

    private static void shortTimeTask(int threadNum) {
        log.info("short thread-{} started",threadNum);
        for (int i = 0; i < 10; i++) {
            log.info("short thread-{} is printing {},Thread:{}",threadNum,i,Thread.currentThread());
        }
        log.info("short thread-{} finished",threadNum);
    }

    private static void longTimeTask(int threadNum) {
        log.info("long thread-{} started",threadNum);
        for (int i = 0; i < 10; i++) {
            log.info("longt hread-{} is printing {},Thread:{}",threadNum,i,Thread.currentThread());
            // 让出CPU,在虚拟线程可以生效，因为shceduler是虚拟线程的调度器,在用户态，我们可以控制 模拟各种条件让出
            Thread.yield();
        }
        log.info("long thread-{} finished",threadNum);
    }

}
