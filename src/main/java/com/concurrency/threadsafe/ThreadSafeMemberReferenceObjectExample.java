package com.concurrency.threadsafe;

public class ThreadSafeMemberReferenceObjectExample {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new MyRunnable(new Company("User"))).start(); // 스레드에 안전, 멤버 변수를 공유하지 않음
        new Thread(new MyRunnable(new Company("User"))).start(); // 스레드에 안전, 멤버 변수를 공유하지 않음

        Thread.sleep(1000);
        System.out.println("-------------");

        Company company = new Company("User"); // 스레드에 안전하지 못함, 멤버 변수를 공유
        new Thread(new MyRunnable(company)).start();
        new Thread(new MyRunnable(company)).start();

    }
}

class MyRunnable implements Runnable {
    private Company company;

    public MyRunnable(Company company) {
        this.company = company;
    }

    @Override
    public void run() {
        company.changeMemberName(Thread.currentThread().getName());
    }
}

class Company {
    private Member member;

    public Company(String userName) {
        this.member = new Member(userName);
    }

    public synchronized void changeMemberName(String name) {
        String oldName = member.getName();
        member.setName(name);
        System.out.println(oldName + ": " + member.getName());
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
