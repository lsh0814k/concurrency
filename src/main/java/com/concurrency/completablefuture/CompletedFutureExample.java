package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletedFutureExample {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("Hello World");

        cf.thenAccept(r -> {
            System.out.println("result: " + r);
        });
    }
}
