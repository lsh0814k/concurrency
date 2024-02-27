package com.concurrency.sync.block;

class MethodBlock {}
public class StaticBlockSynchronizedExample {
    private static int count = 0;

    public static void incrementBlockClass() {
        synchronized (StaticBlockSynchronizedExample.class) {
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 StaticBlockSynchronizedExample 에 의해 블록 동기화. 현재 값 : " + count);
        }
    }

    public static void incrementBlockOtherClass() {
        synchronized (MethodBlock.class) {
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 MethodBlock 에 의해 블록 동기화 함 현재 값 : " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                StaticBlockSynchronizedExample.incrementBlockClass();

            }
        }, "스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                StaticBlockSynchronizedExample.incrementBlockOtherClass();
            }
        }, "스레드 2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();



        System.out.println("최종 값 : " + count);
    }
}
