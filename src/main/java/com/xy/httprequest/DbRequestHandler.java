package com.xy.httprequest;

import java.util.concurrent.Callable;

public class DbRequestHandler implements Callable<String> {

    private int id;
    public DbRequestHandler(int id) {
        this.id = id;
    }

    @Override
    public String call(){
        return requestDB();
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

}
