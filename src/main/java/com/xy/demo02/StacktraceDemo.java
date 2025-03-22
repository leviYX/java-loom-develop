package com.xy.demo02;

import com.xy.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * stack
 */
public class StacktraceDemo {

    private static final Logger log = LoggerFactory.getLogger(StacktraceDemo.class);

    public static void main(String[] args) {
        demo01(Thread.ofVirtual());
        CommonUtils.sleep(Duration.ofSeconds(10));
    }

    private static void demo01(Thread.Builder builder) {
        for (int i = 0; i < 10; i++) {
            int j = i;
            builder.start(()-> Task.execute(j));
        }
    }
}
