package com.concurrency.thread;

public class MultiThreadExample {
    private static Object lock = new Object();
    private static int sum = 0;
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 500; i++) {
                synchronized (lock) {
                    sum += i;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 501; i <= 1000; i++) {
                synchronized (lock) {
                    sum += i;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println("합계 : " + sum);
        System.out.println("멀티 스레드 처리 시간 : " + (System.currentTimeMillis() - start) + "ms");
    }
}
