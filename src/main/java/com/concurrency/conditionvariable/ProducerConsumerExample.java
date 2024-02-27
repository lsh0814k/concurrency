package com.concurrency.conditionvariable;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedQueue queue = new SharedQueue();

        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                try {
                    queue.produce(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "생산자").start();

        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                try {
                    queue.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "소비자").start();

    }
}

class SharedQueue {
    private final Object lock = new Object();
    private Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public void produce(int item) throws InterruptedException {
        synchronized (this.lock) {
            while (queue.size() == CAPACITY) {
                System.out.println("큐가 가득 찼습니다. 생산 중지.");
                this.lock.wait();
            }
            queue.offer(item);
            System.out.println("생산: " + item);

            lock.notifyAll();
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this.lock) {
            while (queue.isEmpty()) {
                System.out.println("큐가 가득 비었습니다. 소비 중지.");
                this.lock.wait();
            }
            Integer item = queue.poll();
            System.out.println("소비: " + item);

            lock.notifyAll();
        }
    }
}
