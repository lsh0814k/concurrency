package com.concurrency.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public class BankAccount {
    private final ReadWriteLock lock;
    private Map<String, Integer> balance = new HashMap<>();


    public BankAccount(ReadWriteLock lock, String account) {
        this.lock = lock;
        balance.put(account, 0);
    }

    public int getBalance(String account) {
        lock.readLock().lock();

        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            return balance.get(account);
        } finally {
          lock.readLock().unlock();
        }
    }

    public void deposit(String account, int amount) {
        lock.writeLock().lock();

        try {
            try {
                balance.put(account, amount + balance.get(account));
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void withdraw(String account, int amount) {
        lock.writeLock().lock();
        try {
            int currentBalance = balance.get(account);
            if (amount <= currentBalance) {
                balance.put(account, currentBalance - amount);
                System.out.println(Thread.currentThread().getName() + " - 출금 성공, 잔액 : " + (currentBalance - amount));
            } else {
                System.out.println(Thread.currentThread().getName() + " - 출금 실패, 잔액 부족");
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}
