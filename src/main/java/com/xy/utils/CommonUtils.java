package com.xy.utils;

import java.time.Duration;

public class CommonUtils {

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long time(Runnable rb) {
        var start = System.currentTimeMillis();
        rb.run();
        return System.currentTimeMillis() - start;
    }
}
