package com.concurrency.group;

public class ThreadGroupInterruptExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup topGroup = new ThreadGroup("상위 그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 그룹");

        Thread topGroupThread = new Thread(topGroup, () -> {
            while (true) {
                System.out.println("상위 그룹 스레드 실행 중");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        }, "상위 그룹 스레드");


        Thread subGroupThread = new Thread(subGroup, () -> {
            while (true) {
                System.out.println("하위 그룹 스레드 실행 중");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        }, "하위 그룹 스레드");

        topGroupThread.start();
        subGroupThread.start();

        Thread.sleep(3000);

        System.out.println("그룹 스레드 중지");


        topGroup.interrupt();
    }
}
