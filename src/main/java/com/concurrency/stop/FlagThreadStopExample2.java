package com.concurrency.stop;

import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadStopExample2 {
    private AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        new FlagThreadStopExample2().flagTest();
    }

    private void flagTest() {
        new Thread(() -> {
            int count = 0;
            while (running.get()) {
                count++;
            }
            System.out.println("Thread 1 종료. Count : " + count);
        }).start();

        new Thread(() -> {
            running.set(false);
            System.out.println("Thread 2 종료");
        }).start();
    }
}
