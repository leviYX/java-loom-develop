package com.xy.demo02;

import com.xy.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void execute(int i) {
        CommonUtils.sleep(Duration.ofMillis(200));
        try {
            method1(i);
        }catch (Exception e) {
            log.error("execute invoke method1 error {},and i is {}", e,i);
        }
    }
    public static void method1(int i) {
        CommonUtils.sleep(Duration.ofMillis(200));
        try {
            method2(i);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void method2(int i) {
        CommonUtils.sleep(Duration.ofMillis(300));
        try {
            method3(i);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void method3(int i) {
        CommonUtils.sleep(Duration.ofMillis(500));
        if (i == 4) {
            throw new IllegalArgumentException("i can not be 4");
        }
    }

}
