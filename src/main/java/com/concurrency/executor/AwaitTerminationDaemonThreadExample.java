package com.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AwaitTerminationDaemonThreadExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2, (r) -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        executorService.submit(() -> {
           while (true) {
               System.out.println(Thread.currentThread().getName() + " 데몬 스레드 실행 중");
               Thread.sleep(1000);
           }
        });

        executorService.shutdown();
        Thread.sleep(3000);

        System.out.println("메인 스레드 종료");
    }
}
