package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenComposeExample {
    public static void main(String[] args) {
        MyService myService = new MyService();
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> cf1 = myService.getData1(5);
        CompletableFuture<Integer> cf2 = cf1.thenCompose(myService::getData2);

        Integer result = cf2.join();

        System.out.println("final Result : " + result);
        System.out.println("소요 시간 : " + (System.currentTimeMillis() - start) + "ms");

    }

    static class MyService {
       public CompletableFuture<Integer> getData1(int input) {
           return CompletableFuture.supplyAsync(() -> {
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               return input * 2;
           });
       }

       public CompletableFuture<Integer> getData2(int input) {
           return CompletableFuture.supplyAsync(() -> {
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               return input * 4;
           });
       }
    }
}
