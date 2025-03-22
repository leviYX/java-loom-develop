//package com.xy.demo06;
//
//import java.util.concurrent.*;
//
//public class TestExecutor {
//
//    static class MyCalculateExecutor implements Executor {
//
//        @Override
//        public void execute(Runnable command) {
//            // 处理cpu密集逻辑
//            System.out.println("my execute");
//        }
//    }
//
//    public static void main(String[] args) {
//        var lanch = new CountDownLatch(5);
//        MyCalculateExecutor executor = new MyCalculateExecutor();
//        var builder = MyBuilder.ofMyVirtual(executor);
//        for (int i = 0; i < 5; i++) {
//            builder.start(() -> {
//                System.out.println(Thread.currentThread());
//                lanch.countDown();
//            });
//        }
//        try {
//            lanch.await();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//}
