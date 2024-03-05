package com.concurrency.policy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallerRunsPolicyExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize= 2;
        long keepAliveTime = 0;
        int workQueueCapacity = 2;

        // CallerRunsPolicy는 queue가 꽉 차고 maxPoolSize 까지 스레드가 생성 되었다면
        // executor 를 실행한 스레드가 해당 작업을 실행 한다.
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(workQueueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy()  // default 값 -> ThreadPoolExecutor.AbortPolicy()
        );

        submitTaks(executor);
    }

    private static void submitTaks(ThreadPoolExecutor executor) {
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
