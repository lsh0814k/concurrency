package com.concurrency.create;

public class ThreadNewExample {
    public static void main(String[] args) {
        // Thread 상속
        Thread myThread = new MyThread();
        myThread.start();

        // Runnable 구현
        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

        // Thread 익명 클래스
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중...");
            }
        }.start();

        // Runnable 익명 클래스
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중...")).start();
    }

    /**
     * Thread를 상속
     */
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중...");
        }
    }

    /**
     * Runnable 구현
     */
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중...");
        }
    }
}
