package com.concurrency.executor;

import java.util.concurrent.Executor;

public class AsyncExecutorExample {
    public static void main(String[] args) {
        AsyncExecutor aSyncExecutor = new AsyncExecutor();
        aSyncExecutor.execute(() -> {
            System.out.println("비동기 작업 1 수행 중..");
            // 작업 수행
            System.out.println("비동기 작업 1 수행 완료..");
        });

        aSyncExecutor.execute(() -> {
            System.out.println("비동기 작업 2 수행 중..");
            // 작업 수행
            System.out.println("비동기 작업 2 수행 완료..");
        });

        System.out.println("메인 스레드 종료");
    }

    static class AsyncExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }
}
