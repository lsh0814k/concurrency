package com.concurrency.callback;

import java.util.concurrent.*;

public class FutureCallbackExample {

    private interface Callback {
        void onComplete(int result);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Integer> callableTask = () -> {
            System.out.println("Callable 작업 수행중");
            Thread.sleep(1000);
            System.out.println("Callable 작업 완료");

            return 42;
        };

        System.out.println("비동기 작업 시작");
        Future<Integer> future = executorService.submit(callableTask);

        registerCallback(future, result -> {
            System.out.println("비동기 작업 결과:" + result);
        });

        executorService.shutdown();
    }

    private static void registerCallback(Future<Integer> future, Callback callback) {
        new Thread(() -> {
            try {
                int result = future.get();
                callback.onComplete(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
