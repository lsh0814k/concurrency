package com.concurrency.schedule;

import java.util.concurrent.*;

public class ScheduleRunnableExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            System.out.println("작업이 한 번 실행되고 결과를 반환합니다.");;
        };

        scheduler.schedule(task, 3, TimeUnit.SECONDS);

        Thread.sleep(5000);

        scheduler.shutdown();
    }
}
