package com.example.customview;

public class LoadingComputations {
    private static final long ANIMATION_LENGTH = 1000;
    private float density;

    public LoadingComputations(float density) {
        this.density = density;
    }

    float dpToPx(int size) {
        return size * density;
    }

    public double verticalPosition(long time, long offset) {
        double X = 2 * Math.PI * (time + offset) / ANIMATION_LENGTH;
        return (Math.sin(X) + 1) / 2.;
    }
}
