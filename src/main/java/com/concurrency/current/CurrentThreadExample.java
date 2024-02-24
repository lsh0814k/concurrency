package com.concurrency.current;

public class CurrentThreadExample {
    public static void main(String[] args) {
        System.out.println("현재 스레드 : " + Thread.currentThread());
        System.out.println("현재 스레드 이름 : " + Thread.currentThread().getName());

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("현재 스레드 : " + getName());
            }
        };
        thread.start();

        Thread runnableThread = new Thread(() -> {
            System.out.println("현재 스레드 : " + Thread.currentThread().getName());
        });

        runnableThread.start();
    }

    static class ThreadName implements Runnable {

        @Override
        public void run() {

        }
    }
}
