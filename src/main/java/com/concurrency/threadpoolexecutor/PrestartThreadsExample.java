package com.concurrency.threadpoolexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PrestartThreadsExample {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        int taskNum = 8;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        // 요청이 오기 전에 하나의 스레드를 미리 생성
        executor.prestartCoreThread();

        // 요청이 오기 전에 corePoolSize 만큼 스레들르 샐성
        //executor.prestartAllCoreThreads();

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 가 " + taskId + " 를 실행하고 있습니다.");
            });
        }

        executor.shutdown();
    }
}
