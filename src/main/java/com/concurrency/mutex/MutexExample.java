package com.concurrency.mutex;

public class MutexExample {
    public static void main(String[] args) throws InterruptedException {
        ShareData shareData = new ShareData(new Mutex());
        Thread thread1 = new Thread(shareData::sum);
        Thread thread2 = new Thread(shareData::sum);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 합계 : " + shareData.getSum());
    }
}
