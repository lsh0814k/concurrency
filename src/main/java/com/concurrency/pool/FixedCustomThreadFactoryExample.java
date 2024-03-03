package com.concurrency.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class FixedCustomThreadFactoryExample {
    public static void main(String[] args) {
        CustomThreadFactory customThreadFactory = new CustomThreadFactory("customThread");
        ExecutorService executorService = Executors.newFixedThreadPool(3, customThreadFactory);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
                System.out.println("thread : " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }

    static class CustomThreadFactory implements ThreadFactory {
        private final String name;
        private int threadCount = 0;
        public CustomThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            threadCount++;
            String threadName = name + " - " + threadCount;
            Thread thread = new Thread(r, threadName);
            System.out.println("스레드 이름 : " + threadName);
            return thread;
        }
    }
}
