package com.concurrency.sync.method;

public class InstanceMethodSynchronizedExample2 {
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
        InstanceMethodSynchronizedExample2 example = new InstanceMethodSynchronizedExample2();
        InstanceMethodSynchronizedExample2 example2 = new InstanceMethodSynchronizedExample2();


        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.increment();
                example2.decrement();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.decrement();
                example2.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("최종 값 : " + example.getCount());
        System.out.println("최종 값 : " + example2.getCount());
    }
}
