package com.concurrency.stop;

public class InterruptedExceptionThreadStopExample4 {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("작업 스레드가 실행 중입니다.");
                    System.out.println("인터럽트 상태 1 : " + Thread.currentThread().isInterrupted());

                    if (Thread.currentThread().isInterrupted()) {
                        // 직접 InterruptedException를 new 해서 던지면 interrupted는 true이다.
                        throw new InterruptedException("thread is interrupted");
                    }
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
