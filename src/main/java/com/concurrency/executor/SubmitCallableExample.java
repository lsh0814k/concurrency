package com.concurrency.executor;

import java.util.concurrent.*;

public class SubmitCallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<String> callableTask = () -> {
            System.out.println("비동기 작업 실행");
            return "Hello World";
        };
        Future<String> future = executorService.submit(callableTask);
        String result = future.get();
        System.out.println("result : " + result);

        executorService.shutdown();
    }
}
