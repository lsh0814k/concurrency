package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        long startTime = System.currentTimeMillis();
        CompletableFuture<Integer> cf = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Thread1: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                    return 40;
                })
                .thenApply(result -> {
                    System.out.println("Thread2: " + Thread.currentThread().getName());
                    int value = myClass.getData();
                    return result + value;
                })
                .thenApplyAsync(result -> {
                    System.out.println("Thread3: " + Thread.currentThread().getName());
                    int value = myClass.getData();
                    return result + value;
                });
        int finalResult = cf.join();
        System.out.println("총 소요 시간: " + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println("finalResult: " + finalResult);
    }

    static class MyClass {
        public int getData() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

            return 50;
        }

        public int getData2() {
            return 50;
        }
    }
}
