package com.example.customview;

public class FakeTimestampProvider implements TimestampProvider {
    private long timeInMillis;

    @Override
    public long timestamp() {
        return timeInMillis;
    }

    public void add(long millis) {
        timeInMillis += millis;
    }
}
