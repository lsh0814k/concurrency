package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteExample {
    public static void main(String[] args) {
        MyService myService = new MyService();
        CompletableFuture<Integer> cf = myService.performTask();
        CompletableFuture<Integer> cf2 = cf.thenApply(r -> r + 20);
        // 만약 cf 또는 cf2 의 result 값이 null 이라면
        // result 값이 null 이 아닐때 까지 대기하게 된다.
        System.out.println("결과 : " + cf2.join());
        System.out.println("메인 스레드 종료");
    }

    static class MyService {
        public CompletableFuture<Integer> performTask() {
            CompletableFuture<Integer> cf = new CompletableFuture<>();
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                cf.complete(40);
            });

            return cf;
        }
    }
}
