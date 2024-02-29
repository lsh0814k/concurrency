package com.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean flag = false;

    public void awaiting() {
        lock.lock();
        try {
            while (!flag) {
                System.out.println("조건이 만족하지 못해 대기함");
                condition.await();
            }
            System.out.println("임계 영역을 추가적으로 수행");
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

    public void signaling() {
        lock.lock();
        try {
            flag = true;
            System.out.println("조건을 만족 시키고 깨움");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample example = new ConditionExample();
        Thread awaitingThread = new Thread(() -> {
            example.awaiting();
        });

        Thread signalingThread = new Thread(() -> {
            example.signaling();
        });

        awaitingThread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        signalingThread.start();

    }
}
