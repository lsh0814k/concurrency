package com.concurrency.forkjoin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ThreadPoolChainedExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        CompletableFuture.supplyAsync(() -> {
            System.out.println("I/O Bound 작업: " + Thread.currentThread().getName());
            return 1;
        }, executorService)

        .thenApplyAsync(res -> {
            System.out.println("CPU Bound 작업: " + Thread.currentThread().getName());
            return res + 1;
        })
        .thenApplyAsync(res -> {
            System.out.println("IO Bound 작업: " + Thread.currentThread().getName());
            return res + 2;
        }, executorService)
        .thenApplyAsync(res -> {
            System.out.println("CPU Bound 작업: " + Thread.currentThread().getName());
            return res + 3;
        })
        .thenComposeAsync(res -> {
            System.out.println("병렬 처리: " + Thread.currentThread().getName());
            return CompletableFuture.supplyAsync(() -> res + 4);
        }, forkJoinPool)
        .thenAcceptAsync(res -> {
            System.out.println("병렬 처리: " + Thread.currentThread().getName());
            System.out.println("최종 결과: " + res);
        }, forkJoinPool).join();

        forkJoinPool.shutdown();
        executorService.shutdown();
    }

}
