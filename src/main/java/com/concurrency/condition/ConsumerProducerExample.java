package com.concurrency.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducerExample {
    private static int CAPACITY = 5;
    private Queue<Integer> queue = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition consumeCondition = lock.newCondition();
    private Condition producerCondition = lock.newCondition();

    public void produce() {
        int value = 0;
        while (true) {
            lock.lock();
            try {
                while (queue.size() == CAPACITY) {
                    System.out.println("큐애 가득 차서 대기함");
                    producerCondition.await();
                }
                queue.offer(value);
                System.out.println("생산 : " + value + ", 큐 크기 : " + queue.size());
                value++;

                consumeCondition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } finally {
                lock.unlock();
            }
        }
    }

    public void consume() {
        while (true) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    System.out.println("큐가 비어 있어 대기함");
                    consumeCondition.await();
                }
                Integer value = queue.poll();
                System.out.println("소비 : " + value + ", 큐 크기: " + queue.size());
                producerCondition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ConsumerProducerExample example = new ConsumerProducerExample();

        Thread producer = new Thread(() -> {
            example.produce();
        });

        Thread consumer = new Thread(() -> {
            example.consume();
        });

        producer.start();
        consumer.start();
    }
}
