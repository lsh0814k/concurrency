package com.concurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {

    public static void main(String[] args) throws InterruptedException {
        User user1 = new User("Alice", 25);
        User user2 = new User("Bob", 30);

        AtomicReference<User> atomicReference = new AtomicReference<>(user1);

        Thread thread1 = new Thread(() -> {
            User updateUser = new User("Carol", 40);
            boolean success = atomicReference.compareAndSet(user1, updateUser);
            if (success) {
                System.out.println("스레드 1이 " + updateUser.getName() + " 로 변경에 성공했습니다.");
            } else {
                System.out.println("스레드 1이 " + updateUser.getName() + " 로 변경에 실패했습니다.");
            }
        });

        Thread thread2 = new Thread(() -> {
            User updateUser = new User("David", 50);
            boolean success = atomicReference.compareAndSet(user2, updateUser);
            if (success) {
                System.out.println("스레드 2가 " + updateUser.getName() + " 로 변경에 성공했습니다.");
            } else {
                System.out.println("스레드 2가 " + updateUser.getName() + " 로 변경에 실패했습니다.");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("final User: " + atomicReference.get().getName());
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
