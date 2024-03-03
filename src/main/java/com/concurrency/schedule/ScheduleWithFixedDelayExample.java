package com.concurrency.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedDelayExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        };

        ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);

        Thread.sleep(10000);

        scheduledFuture.cancel(true);
        scheduler.shutdown();

        System.out.println("취소 완료");
    }
}
