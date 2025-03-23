package com.xy.httprequest;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class UserRequestHandler implements Callable<String> {

    private int id;
    public UserRequestHandler(int id) {
        this.id = id;
    }
    @Override
    public String call(){
        var factory = Thread.ofVirtual().name("real-http-request-vt-", 0).factory();
        try (ExecutorService service = Executors.newThreadPerTaskExecutor(factory)){
            return service.invokeAll(Arrays.asList(this::requestDB, this::requestRest))
                    .stream()
                    .map(
                            future -> {
                                try {
                                    return (String)future.get();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    ).collect(Collectors.joining(","));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String requestDB(){
        NetworkCaller caller = new NetworkCaller("mysql",id);
        try {
            return caller.call(4);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String requestRest(){
        NetworkCaller caller = new NetworkCaller("http-rest",id);
        try {
            return caller.call(2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
