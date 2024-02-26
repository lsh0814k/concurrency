package com.concurrency.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockVsSynchronized {
    private AtomicBoolean spinLock = new AtomicBoolean(false);
    private final Object syncLock = new Object();
    private int count = 0;

    private final static int THREAD_COUNT = 5;
    private final int ITERATIONS = 10_000_000;

    public void useSpinLock() {
        while (!spinLock.compareAndSet(false, true));
        for (int i = 0; i < ITERATIONS; i++) {
            count++;
        }
        spinLock.set(false);
    }

    public void useSynchronized() {
        synchronized (syncLock) {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockVsSynchronized tester = new SpinLockVsSynchronized();

        // synchronized 성능 테스트
        Thread[] syncThreads = new Thread[THREAD_COUNT];
        long start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i] = new Thread(tester::useSynchronized);
            syncThreads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i].join();
        }

        long end = System.currentTimeMillis();
        System.out.println("synchronized 시간 : " + (end - start));

        Thread[] spinThreads = new Thread[THREAD_COUNT];
        start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            spinThreads[i] = new Thread(tester::useSpinLock);
            spinThreads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            spinThreads[i].join();
        }

        end = System.currentTimeMillis();
        System.out.println("spinlock 시간 : " + (end - start));
    }

}
