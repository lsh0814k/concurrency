package com.concurrency.sync.method;

public class InstanceMethodSynchronizedExample {

    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 증가 시켰습니다. 현재 값 : " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 감소 시켰습니다. 현재 값 : " + count);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceMethodSynchronizedExample example = new InstanceMethodSynchronizedExample();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.decrement();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("최종 값 : " + example.getCount());
    }

}
