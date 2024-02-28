package com.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("스레드 1이 락을 획득 했습니다.");
                } finally {
                    System.out.println("스레드 1이 락을 해제 했습니다.");
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("스레드 1이 인터럽트를 받았습니다.");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("스레드 2가 락을 획득 했습니다.");
                } finally {
                    System.out.println("스레드 2가 락을 해제 했습니다.");
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("스레드 2가 인터럽트를 받았습니다.");
            }
        });

        thread1.start();
        thread2.start();
        thread1.interrupt();

        thread1.join();
        thread2.join();
    }
}
