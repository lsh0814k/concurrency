package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class AnyOfExample {
    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA();
        ServiceB serviceB = new ServiceB();
        ServiceC serviceC = new ServiceC();

        long start = System.currentTimeMillis();
        CompletableFuture<Integer> cfA = serviceA.getData();
        CompletableFuture<Integer> cfB = serviceB.getData();
        CompletableFuture<Integer> cfC = serviceC.getData();

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(cfA, cfB, cfC);

        CompletableFuture<Integer> cf = anyOf.thenApply(result -> {
            System.out.println("result = " + result);
            return (int)result * 10;
        });


        System.out.println("Final Result: " + cf.join());
        System.out.println("최종 소요 시간: " + (System.currentTimeMillis() - start));
        System.out.println("메인 스레드 종료");
    }

    static class ServiceA {
        public CompletableFuture<Integer> getData() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("비동기 작업 시작 1");
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                return 10;
            });
        }
    }

    static class ServiceB {
        public CompletableFuture<Integer> getData() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println("비동기 작업 시작 2");
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                return 20;
            });
        }
    }

    static class ServiceC {
        public CompletableFuture<Integer> getData() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("비동기 작업 시작 3");
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                return 30;
            });
        }
    }
}
