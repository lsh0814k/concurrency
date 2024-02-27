package com.concurrency.sync;

public class MultipleMonitorsExample {
    public static void main(String[] args) throws InterruptedException {
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(1000);

        // accountA 에서 accountB로 송금 하는 스레드
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                boolean result = accountA.transfer(accountB, 10);
                if (result) {
                    System.out.println("accountA에서 accountB로 10 송금 성공");
                } else {
                    System.out.println("accountA에서 accountB로 10 송금 실패");
                }
            }
        });

        // accountB 에서 accountA로 송금 하는 스레드
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                boolean result = accountB.transfer(accountA, 10);
                if (result) {
                    System.out.println("accountB에서 accountA로 10 송금 성공");
                } else {
                    System.out.println("accountB에서 accountA로 10 송금 실패");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("accountA의 최종 잔액 : " + accountA.getBalance());
        System.out.println("accountB의 최종 잔액 : " + accountB.getBalance());
    }
}

class BankAccount {
    private long balance;
    private final Object lock = new Object();

    public BankAccount(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        synchronized (this.lock) {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) {
        synchronized (this.lock) {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    public boolean transfer(BankAccount to, long amount) {
        synchronized (this.lock) {
            if (this.withdraw(amount)) {
                synchronized (to.lock) {
                    to.deposit(amount);
                    return true;
                }
            }

            return false;
        }
    }

    public long getBalance() {
        synchronized (this.lock) {
            return balance;
        }
    }
}
