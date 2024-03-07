package com.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start;
    private final int end;

    private static final int THRESHOLD = 2;
    public CustomRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start < THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }

            return sum;
        }

        int mid = start + (end - start) / 2;
        CustomRecursiveTask left = new CustomRecursiveTask(array, start, mid);
        CustomRecursiveTask right = new CustomRecursiveTask(array, mid, end);


        left.fork(); // 별도의 스레드가 해당 작업을 수행

        Integer rightValue = right.compute(); // 현재 스레드가 해당 작업을 수행
        Integer leftResult = left.join();
        return leftResult + rightValue;
    }
}
