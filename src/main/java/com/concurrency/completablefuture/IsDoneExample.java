package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class IsDoneExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return 42;
        });

        CompletableFuture<Integer> cf2 = cf1.thenApply(result -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return result * 2;
        });

        while (!cf1.isDone() || !cf2.isDone()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("작업이 완료되지 않았습니다.");
        }

        Integer result1 = cf1.join();
        Integer result2 = cf2.join();
        System.out.println("첫 번째 결과: " + result1);
        System.out.println("두 번째 결과: " + result2);
    }
}
