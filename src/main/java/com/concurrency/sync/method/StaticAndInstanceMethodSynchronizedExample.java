package com.concurrency.sync.method;

public class StaticAndInstanceMethodSynchronizedExample {

    private int instanceCount = 0;
    private static int staticCount = 0;

    public static synchronized void incrementStaticCount() { // Class 가 모니터가 된다.
        staticCount++;
        System.out.println(Thread.currentThread().getName() + " 가 인스턴스 카운트를 증가 시켰습니다. 현재 값 : " + staticCount);
    }

    public static synchronized void decrementStaticCount() { // Class 가 모니터가 된다.
        staticCount--;
        System.out.println(Thread.currentThread().getName() + " 가 인스턴스 카운트를 감소 시켰습니다. 현재 값 : " + staticCount);
    }

    public synchronized void incrementInstanceCount() { // this 가 모니터가 된다.
        instanceCount++;
        System.out.println(Thread.currentThread().getName() + " 가 정적 카운트를 증가 시켰습니다. 현재 값 : " + instanceCount);
    }

    public synchronized void decrementInstanceCount() { // this 가 모니터가 된다.
        instanceCount--;
        System.out.println(Thread.currentThread().getName() + " 가 정적 카운트를 감소 시켰습니다. 현재 값 : " + instanceCount);
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public static int getStaticCount() {
        return staticCount;
    }

    public static void main(String[] args) throws InterruptedException {
        StaticAndInstanceMethodSynchronizedExample example = new StaticAndInstanceMethodSynchronizedExample();


        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementInstanceCount();

            }
        }, "스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.decrementInstanceCount();
            }
        }, "스레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                StaticAndInstanceMethodSynchronizedExample.incrementStaticCount();
            }
        }, "스레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                StaticAndInstanceMethodSynchronizedExample.decrementStaticCount();
            }
        }, "스레드 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();


        System.out.println("최종 값 : " + example.getInstanceCount());
        System.out.println("최종 값 : " + StaticAndInstanceMethodSynchronizedExample.getStaticCount());
    }
}
