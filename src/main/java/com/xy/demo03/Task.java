package com.xy.demo03;

import com.xy.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void cpuTaskExecute(int i) {
        log.info("cpu task starting thread is: {}", Thread.currentThread());
        var cost = CommonUtils.time(() -> findFib(i));
        log.info("cpu task ending,time cost is: {}", cost);
    }

    private static long findFib(long input) {
        if (input < 2) {
            return input;
        }
        return findFib(input - 1) + findFib(input - 2);
    }
}
