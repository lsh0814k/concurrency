package com.concurrency.fieldupdater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterCASExample {
    private static int NUM_THREADS = 3;
    private static AtomicIntegerFieldUpdater<MyClass> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "counter");

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        MyClass myClass = new MyClass();
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
               for (int j = 0; j < 10000; j++) {
                    int expectedValue;
                    int newValue;
                    do {
                        expectedValue = fieldUpdater.get(myClass);
                        newValue = expectedValue + 1;
                    } while (!fieldUpdater.compareAndSet(myClass, expectedValue, newValue));

                   System.out.println(Thread.currentThread().getName() + ":" + expectedValue + ":" + newValue);
               }
            });
            threads[i].start();
        }

        for (Thread thread: threads) {
            thread.join();
        }

        System.out.println("myClass Final value : " + myClass.getCounter());
        System.out.println("fieldUpdater Final value : " + fieldUpdater.get(myClass));
    }

    static class MyClass {
        private volatile int counter;

        public int getCounter() {
            return counter;
        }
    }
}
