package com.concurrency.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SupplyAsyncExample {
    public static void main(String[] args) {
        MyService myService = new MyService();
        CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 가 비동기 작업을 시작 했습니다.");
            return myService.getData();
        });

        List<Integer> result = cf.join();
        result.stream().forEach(System.out::println);

        System.out.println("메인 스레드 종료");
    }
}

class MyService {
    public List<Integer> getData() {
        return Arrays.asList(1,2,3);
    }
}
