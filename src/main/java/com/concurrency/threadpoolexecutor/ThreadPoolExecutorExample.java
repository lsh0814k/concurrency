package com.concurrency.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {

        // 먼저 스레드가 생성되지 않고 corePoolSize 만큼 요청이 왔을 때 스레드가 corePoolSize 만큼 생성이 된다.
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        // 큐 사이즈 보다 많은 요청이 온 경우 maxPoolSize 만큼 스레드가 생성 된다.
        // 큐 사이즈 + maxPoolSize 보다 요청 수가 많다면 RejectExecutionException 이 발생한다.
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(4);
        int taskNum = 8;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 가 " + taskId + " 를 실행하고 있습니다.");
            });
        }

        executor.shutdown();
    }
}
