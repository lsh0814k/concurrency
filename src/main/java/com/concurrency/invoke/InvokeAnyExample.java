package com.concurrency.invoke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvokeAnyExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = new ArrayList<>();

        tasks.add(() -> {
            Thread.sleep(2000);
            return "Task 1";
        });

        tasks.add(() -> {
            Thread.sleep(1000);
            throw new RuntimeException("error");
        });

        tasks.add(() -> {
            Thread.sleep(3000);
            return "Task 3";
        });

        long start = 0;
        try {
            start = System.currentTimeMillis();
            String result = executorService.invokeAny(tasks);
            System.out.println("result : " + result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
            System.out.println("총 소요 시간 : " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
