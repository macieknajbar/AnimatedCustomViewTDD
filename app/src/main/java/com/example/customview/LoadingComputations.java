package com.example.customview;

public class LoadingComputations {
    private static final long ANIMATION_LENGTH = 1000;
    private static final long ANIMATION_PAUSE = 500;
    private float density;

    public LoadingComputations(float density) {
        this.density = density;
    }

    float dpToPx(int size) {
        return size * density;
    }

    public double verticalPosition(long time, long offset) {
        time = (time + offset) % (ANIMATION_LENGTH + ANIMATION_PAUSE);
        if (time >= ANIMATION_LENGTH) {
            return 0.0;
        }
        double X = 2 * Math.PI * time / ANIMATION_LENGTH;
        return (Math.sin(X - Math.PI/2) + 1) / 2.;
    }
}
