//package com.xy.demo06;
//
//import java.lang.reflect.Constructor;
//import java.util.Objects;
//import java.util.concurrent.Executor;
//
///**
// * hack
// */
//public class MyBuilder {
//    private static final Constructor<Thread.Builder.OfVirtual> constructor;
//
//    static {
//        try {
//            Class<Thread.Builder.OfVirtual> clazz =
//                    (Class<Thread.Builder.OfVirtual>) Class.forName(
//                    "java.lang.ThreadBuilders$VirtualThreadBuilder"
//            );
//            constructor = clazz.getDeclaredConstructor(Executor.class);
//            constructor.setAccessible(true);
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static Thread.Builder.OfVirtual ofMyVirtual(Executor executor) {
//        try {
//            return constructor.newInstance(executor);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
