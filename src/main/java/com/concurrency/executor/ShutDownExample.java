package com.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutDownExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " : 작업 종료");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("인터럽트 걸림");
                    throw new RuntimeException();
                }

                return 42;
            });
        }

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                System.out.println("스레드 풀 강제 종료 수행");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }

        System.out.println("스레드 풀을 종료 여부:" + executorService.isShutdown());

        while (!executorService.isTerminated()) {
            System.out.println("스레드 풀 종료 중..");
        }

        System.out.println("모든 작업이 종료되고 스레드 풀이 종료 됨");
    }
}
