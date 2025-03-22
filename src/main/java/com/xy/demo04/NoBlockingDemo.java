package com.xy.demo04;

import com.xy.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class NoBlockingDemo {

    private static final Logger log = LoggerFactory.getLogger(NoBlockingDemo.class);


    public static void main(String[] args) {
        vtBlocking();

        CommonUtils.sleep(Duration.ofSeconds(10));
    }

    private static void platformBlocking() {
        var builder = Thread.ofPlatform();
        var thread = builder.unstarted(() -> {
            log.info("start");
            getStock();
            getPrice();
            payment();
            log.info("end");
        });

        thread.start();
    }

    private static void vtBlocking() {
        var builder = Thread.ofVirtual();
        var thread = builder.unstarted(() -> {
            log.info("start");
            getStock();
            getPrice();
            payment();
            log.info("end");
        });

        thread.start();
    }

    /**
     * 获取库存
     */
    private static void getStock() {
        log.info("get stock start...");
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("get stock end...");

    }

    /**
     * 获取商品价格
     */
    private static void getPrice() {
        log.info("get price start...");
        CommonUtils.sleep(Duration.ofSeconds(2));
        log.info("get price end...");
    }

    /**
     * 支付
     */
    private static void payment() {
        log.info("payment start...");
        CommonUtils.sleep(Duration.ofSeconds(3));
        log.info("payment end...");
    }
}
