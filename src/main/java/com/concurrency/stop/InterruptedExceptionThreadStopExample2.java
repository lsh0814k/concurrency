package com.concurrency.stop;

public class InterruptedExceptionThreadStopExample2 {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("작업 스레드가 실행 중입니다.");
                    System.out.println("인터럽트 상태 1 : " + Thread.currentThread().isInterrupted());
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("인터럽트 상태 2 : " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
            System.out.println("작업 스레드가 중단 되었습니다.");
            System.out.println("인터럽트 상태 3 : " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            worker.interrupt();
            System.out.println("중단 스레드가 작업 스레드를 중단 시켰습니다.");
        });

        worker.start();
        stopper.start();
    }
}
