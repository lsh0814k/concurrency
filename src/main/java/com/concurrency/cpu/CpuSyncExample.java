package com.concurrency.cpu;

public class CpuSyncExample {
    private static final int ITERATIONS = 10000;
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (CpuSyncExample.class) {
                    count++;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (CpuSyncExample.class) {
                    count++;
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("예상 결과: " + (2 * ITERATIONS));
        System.out.println("실제 결과: " + count);

    }
}
