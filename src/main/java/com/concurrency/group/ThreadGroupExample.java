package com.concurrency.group;

public class ThreadGroupExample {
    public static void main(String[] args) {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup customThreadGroup = new ThreadGroup("Custom Thread Group");

        Thread defaultGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");
        Thread mainGroupThread = new Thread(mainThreadGroup, new GroupRunnable(), "mainGroupThread");
        Thread customGroupThread = new Thread(customThreadGroup, new GroupRunnable(), "customGroupThread");

        defaultGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();

    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + " 는 " + currentThread.getThreadGroup().getName() + "에 속한다.");
        }
    }
}
