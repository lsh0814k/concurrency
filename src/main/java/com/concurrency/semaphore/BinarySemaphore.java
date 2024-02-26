package com.concurrency.semaphore;

public class BinarySemaphore implements CommonSemaphore {
    private int signal = 1;

    @Override
    public synchronized void acquired() {
        while (signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }

        this.signal = 0;
    }

    @Override
    public synchronized void release() {
        notify();
        this.signal = 1;
    }
}
