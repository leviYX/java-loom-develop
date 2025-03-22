package com.xy.demo04;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test {
    public static void main(String[] args) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8180/app/v1/search/getMarriageLicense/3"))
                .GET().build();
        try (var client = HttpClient.newHttpClient();){
            for (int i = 0; i < 100_1000; i++) {
                Thread.ofVirtual().name("test-",1).start(()->{
                    client.sendAsync(request, new HttpResponse.BodyHandler<Object>() {
                        @Override
                        public HttpResponse.BodySubscriber<Object> apply(HttpResponse.ResponseInfo responseInfo) {
                            System.out.println(responseInfo);
                            return null;
                        }
                    });

                });

            }
        }

    }
}
