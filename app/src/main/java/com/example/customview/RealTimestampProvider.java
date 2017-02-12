package com.example.customview;

public class RealTimestampProvider implements TimestampProvider {
    @Override
    public long timestamp() {
        return System.currentTimeMillis();
    }
}
