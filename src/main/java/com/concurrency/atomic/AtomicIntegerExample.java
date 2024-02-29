package com.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    private static AtomicInteger counter = new AtomicInteger(0);
    private static int NUM_THREADS = 3;

    private static int NUM_INCREMENTS = 10000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_INCREMENTS; j++) {
                    counter.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " : " + counter.get());
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        System.out.println("Final Value : " + counter.get());
    }
}
