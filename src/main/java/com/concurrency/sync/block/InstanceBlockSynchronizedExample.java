package com.concurrency.sync.block;

public class InstanceBlockSynchronizedExample {
    private int count = 0;
    private Object lockObject = new Object();

    public void incrementBlockThis() {
        synchronized (this) {
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 This 에 의해 블록 동기화. 현재 값 : " + count);
        }
    }

    public void incrementBlockLockObject() {
        synchronized (lockObject) {
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 lockObject 에 의해 블록 동기화 함 현재 값 : " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceBlockSynchronizedExample example = new InstanceBlockSynchronizedExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockThis();

            }
        }, "스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockThis();
            }
        }, "스레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockLockObject();
            }
        }, "스레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockLockObject();
            }
        }, "스레드 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();


        System.out.println("최종 값 : " + example.count);
    }
}
