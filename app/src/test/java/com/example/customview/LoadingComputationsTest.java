package com.example.customview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadingComputationsTest {

    private static final double DELTA = 0.002;

    private LoadingComputations loadingComputations;

    @Test public void checksComputerAbilityToMultiply() {
        loadingComputations = new LoadingComputations(2.5f);
        assertEquals(2.5f, loadingComputations.dpToPx(1), 0.001);
    }

    @Test public void doubleCheckConversionIsCorrect() {
        loadingComputations = new LoadingComputations(2.5f);
        assertEquals(7.5f, loadingComputations.dpToPx(3), 0.001);
    }

    @Test public void convertForDensityEqual3() {
        loadingComputations = new LoadingComputations(3.f);
        assertEquals(9.f, loadingComputations.dpToPx(3), 0.001);
    }

    @Test public void calculatesSinusoidResultBasedOnTime() {
        loadingComputations = new LoadingComputations(0.f);
        assertEquals((0. + 1)/2, loadingComputations.verticalPosition(0, 250), DELTA);
        assertEquals((1./2 + 1)/2, loadingComputations.verticalPosition(83, 250), DELTA);
        assertEquals((Math.sqrt(2)/2 + 1)/2, loadingComputations.verticalPosition(125, 250), DELTA);
        assertEquals((1 + 1)/2, loadingComputations.verticalPosition(250, 250), DELTA);
    }

    @Test public void calculatesSinusoidResultBasedOnTimeWithOffset125ms() {
        loadingComputations = new LoadingComputations(0.f);
        assertEquals((0. + 1)/2, loadingComputations.verticalPosition(-125, 375L), DELTA);
        assertEquals((Math.sqrt(2)/2 + 1)/2, loadingComputations.verticalPosition(0, 375L), DELTA);
        assertEquals((1 + 1)/2, loadingComputations.verticalPosition(125, 375L), DELTA);
    }

    @Test public void dotsRestTime() {
        loadingComputations = new LoadingComputations(0.f);
        assertEquals(0.f, loadingComputations.verticalPosition(1000, 0), DELTA);
        assertEquals(0.f, loadingComputations.verticalPosition(1250, 0), DELTA);
        assertEquals(0.f, loadingComputations.verticalPosition(1499, 0), DELTA);
    }

    @Test public void dotsResumeAfterAnimationPause() {
        loadingComputations = new LoadingComputations(0.f);
        assertEquals((0. + 1)/2, loadingComputations.verticalPosition(1500, 250), DELTA);
        assertEquals((1./2 + 1)/2, loadingComputations.verticalPosition(1583, 250), DELTA);
        assertEquals((Math.sqrt(2)/2 + 1)/2, loadingComputations.verticalPosition(1625, 250), DELTA);
        assertEquals((1 + 1)/2, loadingComputations.verticalPosition(1750, 250), DELTA);
    }

    @Test public void twoDotsRunningIndependently() {
        loadingComputations = new LoadingComputations(0.f);
        assertEquals((0. + 1)/2, loadingComputations.verticalPosition(0, 250), DELTA);
        assertEquals(0., loadingComputations.verticalPosition(0, 1250), DELTA);
        assertEquals((1./2 + 1)/2, loadingComputations.verticalPosition(83, 250), DELTA);
        assertEquals(0., loadingComputations.verticalPosition(83, 1250), DELTA);
        assertEquals((Math.sqrt(2)/2 + 1)/2, loadingComputations.verticalPosition(125, 250), DELTA);
        assertEquals(0., loadingComputations.verticalPosition(125, 1250), DELTA);
        assertEquals((1 + 1)/2, loadingComputations.verticalPosition(250, 250), DELTA);
        assertEquals(0., loadingComputations.verticalPosition(250, 1250), DELTA);
    }

    @Test public void sinusoidStartsFromTheLowestValue() {
        loadingComputations = new LoadingComputations(0.f);
        assertEquals(0., loadingComputations.verticalPosition(0, 0), DELTA);
        assertEquals((0. + 1)/2, loadingComputations.verticalPosition(250, 0), DELTA);
        assertEquals((1. + 1)/2, loadingComputations.verticalPosition(500, 0), DELTA);
    }
}