package com.example.customview;

import java.util.concurrent.TimeUnit;

public class FramesManager {

    private static final long TIME_PER_FRAME = 1000 / 60;

    private int frames;

    private long lastFrame;
    private long timeSpan;
    private long timeFrame;

    private int fps;

    private TimestampProvider timestampProvider;

    public FramesManager(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    public void frame() {
        if (canGo()) {
            timeFrame %= TIME_PER_FRAME;
        }

        long currentFrame = timestampProvider.timestamp();

        long timeStep = currentFrame - lastFrame;
        timeFrame += timeStep;
        timeSpan += timeStep;
        if (timeSpan > TimeUnit.SECONDS.toMillis(1)) {
            timeSpan = 0;
            fps = getFramesCount();
            frames = 0;
        }

        if (canGo()) {
            frames++;
        }
        lastFrame = currentFrame;
    }

    public int getFramesCount() {
        return frames;
    }

    public int fps() {
        return fps;
    }

    public boolean canGo() {
        return timeFrame > TIME_PER_FRAME;
    }
}
