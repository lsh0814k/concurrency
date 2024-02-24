package com.concurrency.stop;

public class ThreadStopExample {
    private static boolean running = true;
    public static void main(String[] args) {
        new Thread(() -> {
            int count = 0;
            while (running) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
                count++;
            }
            System.out.println("스레드 1 종료, count : " + count);
        }).start();


        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            running = false;
            System.out.println("스레드 2 종료");
        }).start();
    }
}
