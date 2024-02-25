package com.concurrency.cpu;

public class CpuNonSyncExample {
    private static final int ITERATIONS = 10000;
    private static int count = 0;

    /**
     * 공유 데이터를 변경하고 있다.
     * CPU의 원자성을 보장할 수 없는 코드계
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("예상 결과: " + (2 * ITERATIONS));
        System.out.println("실제 결과: " + count);

    }
}
