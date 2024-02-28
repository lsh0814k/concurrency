package com.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockOrderExample {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();
    public static void main(String[] args) {
        // 락 획득 순서와 상관 없이 락을 해제할 수 있다.
        new Thread(() -> {
            System.out.println("스레드가 1번 락을 획득 했습니다.");
            lock1.lock();
            try {
                System.out.println("스레드가 2번 락을 획득 했습니다.");
                lock2.lock();
                try {

                } finally {
                    System.out.println("스레드가 1번 락을 해제 했습니다.");
                    lock1.unlock();
                }
            } finally {
                System.out.println("스레드가 2번 락을 해제 했습니다.");
                lock2.unlock();
            }
        }).start();
    }

}
