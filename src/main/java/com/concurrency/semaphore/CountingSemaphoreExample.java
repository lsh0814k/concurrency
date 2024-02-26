package com.concurrency.semaphore;

public class CountingSemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        ShareResource shareResource = new ShareResource(new CountingSemaphore(10));

        int threadCount = 11;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(shareResource::sum);
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        System.out.println("최종 값 : " + shareResource.getSum());
    }
}
