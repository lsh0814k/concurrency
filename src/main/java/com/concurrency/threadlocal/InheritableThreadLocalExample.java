package com.concurrency.threadlocal;

public class InheritableThreadLocalExample {
    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        inheritableThreadLocal.set("부모 스레드 값");

        Thread childThread = new Thread(() -> {
            System.out.println("자식 스레드에서 부모로부터 상속 받은 값 : " + inheritableThreadLocal.get());
            inheritableThreadLocal.set("자식 스레드 값");
            System.out.println("자식 스레드에서 설정한 후의 값 : " + inheritableThreadLocal.get());
        });

        childThread.start();

        childThread.join();

        System.out.println("부모 스레드의 값 : " + inheritableThreadLocal.get());
    }
}
