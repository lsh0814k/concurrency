package com.concurrency.hook;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorHook extends ThreadPoolExecutor {
    public ThreadPoolExecutorHook(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(Thread.currentThread().getName() + " 가 작업을 실행 하려고 합니다.");
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t == null) {
            System.out.println(Thread.currentThread().getName() + " 가 작업을 성공적으로 완료 했습니다.");
        } else {
            System.out.println(Thread.currentThread().getName() + " 가 작업 도중 " + t.getMessage() + " 예외가 발생했습니다.");
        }
        super.afterExecute(r, t);
    }

    @Override
    protected void terminated() {
        System.out.println("스레드 풀이 종료 되었습니다.");
        super.terminated();
    }

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize= 2;
        long keepAliveTime = 0;
        int workQueueCapacity = 2;


        ThreadPoolExecutor executor = new ThreadPoolExecutorHook(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(workQueueCapacity)
        );

        for (int i = 0; i < 4; i++) {
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
