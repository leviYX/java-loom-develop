package com.xy.demo01;

import java.util.concurrent.CountDownLatch;

/**
 * continution = vt
 * schedule = forkjoinpool
 */
public class IOTaskDemo {

    // 平台线程最大数量
    private static final int MAX_PLATFORM_THREAD_NUM = 10_0000;

    // 虚拟线程最大数量
    private static final int MAX_VIRTUAL_THREAD_NUM = 15;

    public static void main(String[] args) throws InterruptedException {
        virtualThreadDemo1();
    }

    public static void virtualThreadDemo1() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL_THREAD_NUM);
        // 创建虚拟线程构造器
        var builder = Thread.ofVirtual().name("virtual-thread-",1);
        for (int i = 0; i < MAX_VIRTUAL_THREAD_NUM; i++) {
            var j = i;
            var thread = builder.unstarted(()->{
                Task.ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }

    public static void virtualThreadDemo2() throws InterruptedException {
        // 创建虚拟线程构造器
        var builder = Thread.ofVirtual().name("virtual-thread-",1);
        for (int i = 0; i < MAX_VIRTUAL_THREAD_NUM; i++) {
            var j = i;
            var thread = builder.unstarted(()->{
                Task.ioIntensive(j);
            });
            thread.start();
        }
    }


    private static void platformThreadDemo1() {
        for (int i = 0; i < MAX_PLATFORM_THREAD_NUM; i++) {
            /*
             * lambda只能捕获final或effectively final类型的变量，这里的i不是final类型，所以无法捕获
             * 需要声明一个新的变量j，然后捕获j，这个j看着不是final类型，但是实际上是final的
             * 译器会把这个j当成final来处理，字节码可以看到
             */
            int j = i;
            new Thread(() -> Task.ioIntensive(j)).start();
        }
    }

    private static void platformThreadDemo2() {
        // Thread.Builder.OfPlatform builder = Thread.ofPlatform();
        // 创建一个平台线程的构造器，用来创建平台线程
        var builder = Thread.ofPlatform();
        // 设置线程名称前缀为"my-platform-thread-"，并设置线程的编号起始值为100
        builder.name("my-platform-thread-",100);
        // 该构造器创建的线程都是非守护线程，你不指定的话默认也是非守护线程
        builder.daemon(false);
        // 设置线程的堆栈大小为100
        builder.stackSize(100);
        // 定义线程的异常回调处理器
        builder.uncaughtExceptionHandler((t, e) -> System.out.println("Thread " + t.getName()
                + " throws an exception: " + e.getMessage()));
        for (int i = 0; i < MAX_PLATFORM_THREAD_NUM; i++) {
            int j = i;
            // 已未启动模式创建线程，你也可以直接创建启动的线程，直接使用builder.start()。这样的线程创建就启动，不需要再调用start()方法
            Thread thread = builder.unstarted(() -> Task.ioIntensive(j));
            // 未启动线程需要显式的调用start()方法启动
            thread.start();
        }
    }

}
