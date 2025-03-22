package com.xy.demo01;


import com.xy.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    private static final int DEFAULT_IO_INTENSIVE_SLEEP_SECONDS = 2;


    /**
     * 模拟I/O密集型任务
     * @param i 任务编号
     */
    public static void ioIntensive(int i) {
        log.info("starting I/O task  {},current Thread info {}", i,Thread.currentThread());
        CommonUtils.sleep(Duration.ofSeconds(DEFAULT_IO_INTENSIVE_SLEEP_SECONDS));
        log.info("ending   I/O task  {},current Thread info {}", i,Thread.currentThread());
    }
}
