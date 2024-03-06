package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenCombineExample {
    public static void main(String[] args) {
        MyService myService = new MyService();
        CompletableFuture<String> cf1 = myService.getData1();
        CompletableFuture<String> cf2 = myService.getData2();

        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, (result1, result2) -> result1 + result2);
        System.out.println("cf3 결과 : " + cf3.join());

        CompletableFuture<String> cf4 = cf3.thenCompose(result -> CompletableFuture.supplyAsync(() -> result + " Java"));
        System.out.println("cf4 결과 : " + cf4.join());

    }

    static class MyService {
        public CompletableFuture<String> getData1() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Hello ";
            });
        }

        public CompletableFuture<String> getData2() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "world";
            });
        }
    }
}
