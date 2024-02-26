package com.concurrency.monitor;

public class ConditionSynchronizationExample {

    private boolean isAvailable = false;

    private synchronized void produce() {
        while (isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        System.out.println("생상됨");
        isAvailable = true;
        notify();
    }

    private synchronized void consume() {
        while (!isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        System.out.println("소비됨");
        isAvailable = false;
        notify();
    }

    public static void main(String[] args) {
        ConditionSynchronizationExample example = new ConditionSynchronizationExample();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.produce();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.consume();
            }
        }).start();
    }
}
