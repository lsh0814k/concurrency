package com.concurrency.mutex;

public class ShareData {
    private int sharedValue = 0;

    private Mutex mutes;

    public ShareData(Mutex mutes) {
        this.mutes = mutes;
    }

    public void sum() {
        try {
            mutes.acquired();
            for (int i = 0; i < 10000000; i++) {
                sharedValue++;
            }
        } finally {
            mutes.release();
        }
    }

    public int getSum() {
        return this.sharedValue;
    }
}
