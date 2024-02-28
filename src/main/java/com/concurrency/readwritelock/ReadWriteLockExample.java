package com.concurrency.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        SharedData sharedData = new SharedData();
        Thread reader = new Thread(() -> {
            lock.readLock().lock();
            try {
                System.out.println("읽기 스레드 데이터를 읽고 있습니다. 데이터 : " + sharedData.getData());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            } finally {
                lock.readLock().unlock();
            }
        });

        Thread reader2 = new Thread(() -> {
            lock.readLock().lock();
            try {
                System.out.println("읽기 스레드2 데이터를 읽고 있습니다. 데이터 : " + sharedData.getData());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            } finally {
                lock.readLock().unlock();
            }
        });

        Thread writer = new Thread(() -> {
            lock.writeLock().lock();
            try {
                System.out.println("쓰기 스레드가 데이터를 쓰고 있습니다");
                sharedData.setData("update data");
                System.out.println("쓰기 스레드가 데이터를 변경 했습니다. 데이터: " + sharedData.getData());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            } finally {
                lock.writeLock().unlock();
            }
        });

        writer.start();
        reader.start();
        reader2.start();

    }

    static class SharedData {
        private String data = "data";

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
