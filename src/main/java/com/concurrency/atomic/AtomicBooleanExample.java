package com.concurrency.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {
    private static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("스레드 1이 바쁜 대기 중.. flag : " + flag.get());
                }

                System.out.println("스레드 1이 임계 영역 수행 중..");
                flag.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("스레드 2가 바쁜 대기 중.. flag : " + flag.get());
                }

                System.out.println("스레드 2가 임계 영역 수행 중..");
                flag.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
