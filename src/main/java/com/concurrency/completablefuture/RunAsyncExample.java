package com.concurrency.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RunAsyncExample {
    public static void main(String[] args) {
        MyRunService myRunService = new MyRunService();

        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 가 비동기 작업을 시작 합니다.");
            List<Integer> result = myRunService.getData();
            result.stream().forEach(System.out::println);
        }).join();

        System.out.println("메인 스레드 종료");
    }
}
class MyRunService {
    public List<Integer> getData() {
        return Arrays.asList(1,2,3);
    }
}