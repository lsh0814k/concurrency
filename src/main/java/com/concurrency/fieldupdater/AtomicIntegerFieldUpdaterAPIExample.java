package com.concurrency.fieldupdater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterAPIExample {

    private static AtomicIntegerFieldUpdater<MyClass> integerFieldUpdater;
    private static AtomicReferenceFieldUpdater<MyClass, String> referenceFieldUpdater;

    public static void main(String[] args) {
        integerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "intField");
        referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(MyClass.class, String.class, "stringField");

        MyClass myClass = new MyClass();
        integerFieldUpdater.addAndGet(myClass, 40); // 원자성 보장
        referenceFieldUpdater.compareAndSet(myClass, null, "myField");

        System.out.println("업데이트 결과: " + myClass.getIntField());
        System.out.println("업데이트 결과: " + myClass.getStringField());
    }

    static class MyClass {
        private volatile int intField;
        private volatile String stringField;

        public int getIntField() {
            return intField;
        }

        public String getStringField() {
            return stringField;
        }
    }
}
