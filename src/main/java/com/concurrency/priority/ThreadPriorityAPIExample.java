package com.concurrency.priority;

public class ThreadPriorityAPIExample {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("기본 우선 순위 : " + thread.getPriority());
        thread.start();

        Thread minThread = new Thread(() -> {
            System.out.println("최소 우선 순위 : " + Thread.currentThread().getPriority());
        });

        minThread.setPriority(Thread.MIN_PRIORITY);
        minThread.start();

        Thread normThread = new Thread(() -> {
            System.out.println("기본 우선 순위 : " + Thread.currentThread().getPriority());
        });
        normThread.setPriority(Thread.NORM_PRIORITY);
        normThread.start();

        Thread maxThread = new Thread(() -> {
            System.out.println("최대 우선 순위 : " + Thread.currentThread().getPriority());
        });

        maxThread.setPriority(Thread.MAX_PRIORITY);
        maxThread.start();
    }
}
