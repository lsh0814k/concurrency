package com.concurrency.threadpoolexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KeepAliveTimeExample {
    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 1;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        int taskNum = 6;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }

                System.out.println(Thread.currentThread().getName() + " 가 " + taskId + " 를 실행하고 있습니다.");
            });
        }

        // keepAliveTime이 지나면 maxPoolSize 만큼 추가된 스레드 뿐만 아니라 모든 스레드가 제거된다.
        executor.allowCoreThreadTimeOut(true);

        Thread.sleep(4000);
        executor.shutdown();
    }
}
