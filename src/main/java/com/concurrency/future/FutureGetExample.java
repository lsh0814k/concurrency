package com.concurrency.future;

import java.util.concurrent.*;

public class FutureGetExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작");
            Thread.sleep(2000);
            System.out.println("비동기 작업 완료");
            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);

        while (!future.isDone()) {
            System.out.println("작업을 기다리는 중");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Integer result = future.get();
            System.out.println("result = " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
