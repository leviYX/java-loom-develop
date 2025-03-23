package com.xy.httprequest;

import java.util.concurrent.Callable;


public class RestRequestHandler implements Callable<String> {

    private int id;
    public RestRequestHandler(int id) {
        this.id = id;
    }

    @Override
    public String call(){
        return requestRest();
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
