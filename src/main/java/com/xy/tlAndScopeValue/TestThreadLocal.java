package com.xy.tlAndScopeValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TestThreadLocal {

    private static final Logger log = LoggerFactory.getLogger(TestThreadLocal.class);

    private static final ThreadLocal<String> AUTH_TL = new ThreadLocal<>();

    public static void main(String[] args) {

        login();
        controller();
        final String pig = "length: 10";
        final String dog = "length:"+ pig.length();
        System.out.println(dog.equals(pig));
    }

    private static String login() {
        var token = UUID.randomUUID().toString();
        AUTH_TL.set(token);
        return token;
    }

    private static void controller() {
        var token = AUTH_TL.get();
        if (token == null) {
            throw new RuntimeException("未登录");
        }
        log.info("controller invoke and token:{}", token);
        service();
    }

    private static void service() {
        var token = AUTH_TL.get();
        var orderDto = getOrderInfo();
        log.info("service invoke and order:{},token:{}", orderDto,token);
    }

    private static OrderDto getOrderInfo(){
        var orderDto = new OrderDto(1, "order-name");
        return orderDto;
    }



}
