package com.concurrency.future;

import java.util.concurrent.*;

public class FutureCancelExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작");
            Thread.sleep(5000);
            System.out.println("비동기 작업 완료");

            return 42;
        };
        Future<Integer> future = executorService.submit(callableTask);
        Thread.sleep(1000);
        future.cancel(true);

        if (! future.isCancelled()) {
            try {
                Integer result = future.get();
                System.out.println("result = " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } catch (ExecutionException e) {
                throw new RuntimeException();
            }
        } else {
            System.out.println("작업이 취소 되었습니다.");
        }



        executorService.shutdown();
    }
}
