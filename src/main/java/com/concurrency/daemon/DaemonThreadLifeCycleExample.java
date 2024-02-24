package com.concurrency.daemon;

public class DaemonThreadLifeCycleExample {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("유저 스레드 실행 중");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("데몬 스레드 실행 중");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        userThread.start();
        daemonThread.setDaemon(true);
        daemonThread.start();

        userThread.join();

        System.out.println("메인 스레드 종료");
    }
}
