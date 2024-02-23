package com.concurrency.sleep;

public class InterruptSleepExample {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("20초 동안 잠듭니다. 인터럽트 되지 않는다면 계속 잠들어 있을 것 입니다.");
                Thread.sleep(20000);
                System.out.println("인터럽트 없이 잠에서 깨었습니다.");
            } catch (InterruptedException e) {
                System.out.println("잠들어 있는 동안 인터럽트 되었습니다!");
            }
        });

        sleepingThread.start();

        Thread.sleep(1000);

        sleepingThread.interrupt();
    }
}
