package com.xy.httprequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URI;

public class NetworkCaller {

    private static final Logger log = LoggerFactory.getLogger(NetworkCaller.class);

    private String callName;
    private int id;

    public NetworkCaller(String callName, int id) {
        this.callName = callName;
        this.id = id;
    }

    public String call(int delaySec) throws Exception {
        log.info("{} call start,id is {},and current thread is {}", callName,id, Thread.currentThread());
        try {
            URI uri = new URI("http://httpbin.org/delay/" + delaySec);
            try (InputStream stream = uri.toURL().openStream()){
                log.info("{} call end,id is {},and current thread is {}", callName,id, Thread.currentThread());
                return new String(stream.readAllBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
