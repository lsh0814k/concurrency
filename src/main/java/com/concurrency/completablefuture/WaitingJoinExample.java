package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class WaitingJoinExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            slee(3000);
            System.out.println("비동기 시작");
            return 1;
        }).thenApplyAsync(r -> {
            sleep(3000);
            System.out.println("비동기 실행 1");
            return r + 2;
        }).thenApplyAsync(r -> {
            sleep(3000);
            System.out.println("비동기 실행 2");
            return r + 3;
        }).thenAccept(r -> {
            sleep(3000);
            System.out.println("최종 결과:" + r);
        });

        sleep(1000);
        cf.join();
        System.out.println("메인 스레드 종료");

    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
