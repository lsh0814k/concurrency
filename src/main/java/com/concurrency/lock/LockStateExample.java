package com.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExample {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("스레드1 이 락을 1번 획득 했습니다.");
            lock.lock();
            try {
                try {
                    System.out.println("스레드1 이 락을 2번 획득 했습니다.");
                    lock.lock();
                    try {
                        System.out.println("스레드1 이 락을 3번 획득 했습니다.");
                        lock.lock();
                    } finally {
                        System.out.println("스레드1 이 락을 1번 해제 했습니다.");
                        lock.unlock();
                    }
                } finally {
                    System.out.println("스레드1 이 락을 2번 해제 했습니다.");
                    lock.unlock();
                }

            } finally {
                System.out.println("스레드1 이 락을 3번 해제 했습니다.");
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("스레드 2가 락을 획득 했습니다.");
            } finally {
                lock.unlock();
                System.out.println("스레드 2가 락을 해제 했습니다.");
            }
        }).start();
    }
}
