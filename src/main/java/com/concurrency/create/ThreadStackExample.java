package com.concurrency.create;

public class ThreadStackExample {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }
    }

    // 스레드 별로 stack 영역이 생성되고 heap, data, code 영역은 공유된다.
    static class MyRunnable implements Runnable {
        private final int threadId;
        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중...");
        }

        private void firstMethod(int threadId) {
            int localValue = threadId + 100;
            secondMethod(localValue);
        }

        private void secondMethod(int localValue) {
            // 스레드 별로 Heap 영역에 새롭게 생성
            // 참조 값을 저장하 있는 변수는 stack 영역에 저장 된다.
            String objectReference = "hello " + threadId;

            System.out.println(Thread.currentThread().getName() + " : 스레드 ID : " + threadId + ", Value : " + localValue);
        }
    }
}
