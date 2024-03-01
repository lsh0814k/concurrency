package com.concurrency.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchLockExample {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Lock lock = new ReentrantLock();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                try {
                    lock.lock();
                    for (int j = 0; j < 50000; j++) {
                        count++;
                    }
                } finally {
                    lock.unlock();
                    countDownLatch.countDown();
                }
            });

            threads[i].start();
        }

        countDownLatch.await();
        System.out.println(count);
    }
}
