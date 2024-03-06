package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenRunExample {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
                System.out.println("thread1: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }

                return 42;
            })
            .thenAccept(result -> {
                System.out.println("결과 : " + result);
            })
            .thenRun(() -> {
                System.out.println("비동기 작업이 완료 되었습니다.");
            }).join();
    }
}
