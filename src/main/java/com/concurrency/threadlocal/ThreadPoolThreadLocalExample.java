package com.concurrency.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);// 2개의 스레드를 가진 스레드 풀 생성

        // 첫 번째 작업 : ThreadLocal 값을 설정
        executor.submit(() -> {
            threadLocal.set("작업 1의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            // threadLocal을 사용했으면 반드시 remove를 해줘야 한다.
            threadLocal.remove();
        });

        // 잠시 대기
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 여러 번의 두 번째 작업 : ThreadLocal 값을 설정 하지 않고 바로 값을 가져와 출력
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            });
        }

        executor.shutdown();
    }
}
