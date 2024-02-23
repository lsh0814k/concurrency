package com.concurrency.state;

public class BlockedStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                while(true) {

                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("락을 획득하려 함");
            }
        });

        thread.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);
        System.out.println("스레드 상태 : " + thread2.getState());
    }
}
