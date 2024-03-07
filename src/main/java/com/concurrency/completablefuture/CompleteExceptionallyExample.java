package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompleteExceptionallyExample {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = new CompletableFuture<>();
        setData(cf1);
        CompletableFuture<String> cf2 = cf1.thenApply(result -> {
                    System.out.println(result);
                    return result.toUpperCase();
                })
                .handle((r, ex) -> {
                    if (ex != null) {
                        System.out.println("Exception: " + ex.getMessage());
                        return "noname";
                    }
                    return r;
                });

        System.out.println("result: " + cf2.join());
    }

    private static void setData(CompletableFuture<String> cf) {
        try {
            System.out.println("비동기 작업 수행 중..");
            Thread.sleep(500);
            throw new IllegalArgumentException("error");
        } catch (Exception e) {
            cf.completeExceptionally(e);
        }

        cf.complete("Hello World");
    }
}
