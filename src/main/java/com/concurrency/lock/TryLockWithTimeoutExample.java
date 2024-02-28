package com.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockWithTimeoutExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("스레드 1이 락을 획득 했습니다.");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    } finally {
                        System.out.println("스레드 1이 락을 해제 했습니다.");
                        lock.unlock();
                    }
                } else {
                    System.out.println("스레드 1이 락을 획득하지 못했습니다.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("스레드 2가 락을 획득 했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    } finally {
                        System.out.println("스레드 2가 락을 해제 했습니다.");
                        lock.unlock();
                    }
                } else {
                    System.out.println("스레드 2가 락을 획득하지 못했습니다.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
