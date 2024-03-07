package com.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CustomForkJoinPoolExample {
    public static void main(String[] args) {
        int[] array = new int[13];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        RecursiveTask<Integer> task = new CustomRecursiveTask(array, 0, array.length);
        Integer result = pool.invoke(task);

        System.out.println("result : " + result);
    }
}

