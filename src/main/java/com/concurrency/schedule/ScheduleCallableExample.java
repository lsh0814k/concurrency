package com.concurrency.schedule;

import java.util.concurrent.*;

public class ScheduleCallableExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Callable<String> task = () -> {
            return "작업이 한 번 실행되고 결과를 반환합니다.";
        };

        ScheduledFuture<String> future = scheduler.schedule(task, 3, TimeUnit.SECONDS);

        try {
            String result = future.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
