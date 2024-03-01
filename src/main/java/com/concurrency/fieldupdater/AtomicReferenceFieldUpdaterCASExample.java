package com.concurrency.fieldupdater;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterCASExample {
    private static AtomicReferenceFieldUpdater<AtomicReferenceFieldUpdaterCASExample, String> messageUpdater =
            AtomicReferenceFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterCASExample.class, String.class,"message");

    private volatile String message;

    public void doSomething() {
        if (messageUpdater.compareAndSet(this, null, "Hello wolrd")) {
            for (int i = 0; i < 10; i++) {
                System.out.println(messageUpdater.get(this));
            }
            message = null;
        } else {
            System.out.println("현재 스레드는 들어오지 못합니다.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicReferenceFieldUpdaterCASExample example = new AtomicReferenceFieldUpdaterCASExample();

        new Thread(() -> {
            example.doSomething();
        }).start();

        new Thread(() -> {
            example.doSomething();
        }).start();
    }
}
