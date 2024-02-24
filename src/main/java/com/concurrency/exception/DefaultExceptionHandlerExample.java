package com.concurrency.exception;

public class DefaultExceptionHandlerExample {
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(((t, e) -> {
            System.out.println(t.getName() + "에서 예외 발생 : " + e.getMessage());
        }));

        Thread thread1 = new Thread(() -> {
            throw new RuntimeException("스레드 1 예외!");
        });

        Thread thread2 = new Thread(() -> {
            throw new RuntimeException("스레드 2 예외!");
        });

        thread1.start();
        thread2.start();
    }
}
