package com.concurrency.create;

public class ThreadStartRunExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행중...");
        });

        thread.start();

        System.out.println(Thread.currentThread().getName() + ": 메인 스레드 종료");
    }
}
