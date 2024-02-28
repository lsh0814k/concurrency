package com.concurrency.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockWriteLockExample {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        BankAccount account = new BankAccount(lock, "account1");
        // 읽기 스레드가 잔액 조회
        new Thread(() -> {
            int balance = account.getBalance("account1");
            System.out.println(Thread.currentThread().getName() + " - 현재 잔액 : " + balance);
        }).start();

        // 쓰기 스레드가 입금
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int depositAmount = (int)(Math.random() * 1000);
                account.deposit("account1", depositAmount);
                System.out.println(Thread.currentThread().getName() + " - 입금 : " + depositAmount);
            }).start();
        }

        // 읽기 스레드가 잔액 조회
        new Thread(() -> {
            int balance = account.getBalance("account1");
            System.out.println(Thread.currentThread().getName() + " - 읽기 스레드 현재 잔액 : " + balance);
        }).start();

        // 쓰기 스레드가 출금
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int withdrawAmount = (int)(Math.random() * 1000);
                account.withdraw("account1", withdrawAmount);
            }).start();
        }

        // 읽기 스레드가 잔액 조회
        new Thread(() -> {
            int balance = account.getBalance("account1");
            System.out.println(Thread.currentThread().getName() + " - 읽기 쓰레드 현재 잔액 : " + balance);
        }).start();
    }
}
