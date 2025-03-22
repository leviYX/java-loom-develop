package com.xy.demo01;

public class ThreadDemo {
    public static void main(String[] args) {
        // r 任务 continution
        var r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        // 线程  调度器 = os
        var t = new Thread(r);
        t.start();
    }
}
