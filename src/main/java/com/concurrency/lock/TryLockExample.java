package com.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            boolean acquired = false;
            while (!acquired) {
                acquired = lock.tryLock();
                if (acquired) {
                    System.out.println("스레드 1이 락을 획득 했습니다.");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    } finally {
                        System.out.println("스레드 1이 락을 해제 했습니다.");
                        lock.unlock();
                    }
                } else {
                    System.out.println("스레드 1이 락을 획득 하지 못했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean acquired = false;
            while (!acquired) {
                acquired = lock.tryLock();
                if (acquired) {

                    try {
                        System.out.println("스레드 2가 락을 획득 했습니다.");
                    } finally {
                        System.out.println("스레드 2가 락을 해제 했습니다.");
                        lock.unlock();
                    }
                } else {
                    System.out.println("스레드 2가 락을 획득 하지 못했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
