package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class IsCompletedExceptionallyAndIsCancelledExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("error");
            //return 20;
        });

        // cf2.cancel(true);

        CompletableFuture<Integer> combine = cf1.thenCombine(cf2.exceptionally(e -> 15), (r1, r2) -> {
            if (cf2.isCancelled()) {
                return 0; // 취소 완료
            } else if (cf2.isCompletedExceptionally()) {
                return r1; // 예외 완료
            } else if (cf2.isDone()) {
                return r1 + r2; // 정상 완료
            }

            return -1;

        });

        System.out.println("최종 결과 : " + combine.join());
    }
}
