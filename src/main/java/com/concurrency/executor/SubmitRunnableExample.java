package com.concurrency.executor;

import java.util.concurrent.*;

public class SubmitRunnableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> future = executorService.submit(() -> {
            System.out.println("Runnable 비동기 작업 실행 중");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Hello World");

        future.cancel(true);

        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (CancellationException e) {
            System.out.println("취소 되었습니다.");
        }
        System.out.println("result : " + result);

        executorService.shutdown();
    }
}
