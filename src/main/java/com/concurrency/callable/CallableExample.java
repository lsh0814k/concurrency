package com.concurrency.callable;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Integer> callableTask = () -> {
            System.out.println("Callable 작업 수행중.");
            System.out.println("Callable 작업 완료");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        try {
            Integer result = future.get();
            System.out.println(" callable 작업 결과 : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
