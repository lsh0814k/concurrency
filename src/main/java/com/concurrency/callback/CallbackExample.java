package com.concurrency.callback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {
    interface Callback {
        void onComplete(int result);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("비동기 작업 시작");
        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

            int result = 42;

            Callback callback = (r) -> {
                System.out.println("비동기 작업 결과 : " + r);
            };
            callback.onComplete(result);
        });


        executorService.shutdown();
    }
}
