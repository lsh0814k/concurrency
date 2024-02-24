package com.concurrency.stop;

public class FlagThreadStopExample {
    private volatile static boolean running = true;
    public static void main(String[] args) {
        new Thread(() -> {
            int count = 0;
            while (running) {
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
