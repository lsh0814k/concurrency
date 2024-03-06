package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {
    public static void main(String[] args) {
        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("thread1: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                    return 40;
                })
                .thenAccept(r -> {
                    System.out.println("thread2: " +Thread.currentThread().getName());
                    System.out.println("결과: " + r);
                }).thenAcceptAsync(r -> {
                    System.out.println("thread3: " +Thread.currentThread().getName());
                    System.out.println("결과: " + r);
                }).join();
    }
}
