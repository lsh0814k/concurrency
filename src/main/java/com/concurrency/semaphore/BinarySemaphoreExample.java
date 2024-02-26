package com.concurrency.semaphore;

public class BinarySemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        ShareResource shareResource = new ShareResource(new BinarySemaphore());

        Thread thread1 = new Thread(shareResource::sum);
        Thread thread2 = new Thread(shareResource::sum);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 합계 : " + shareResource.getSum());
    }
}

class ShareResource {
    private int sharedValue = 0;

    private CommonSemaphore semaphore;

    public ShareResource(CommonSemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void sum() {
        try {
            semaphore.acquired();
            for (int i = 0; i < 10000000; i++) {
                sharedValue++;
            }
        } finally {
            semaphore.release();
        }
    }

    public int getSum() {
        return this.sharedValue;
    }
}
