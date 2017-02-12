package com.example.customview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FramesManagerTest {

    private static final long TIME_PER_FRAME = 1000 / 60 + 1;

    private FramesManager framesManager;
    private FakeTimestampProvider fakeTimestampProvider;

    @Before public void setUp() {
        fakeTimestampProvider = new FakeTimestampProvider();
        framesManager = new FramesManager(fakeTimestampProvider);
        framesManager.frame();  // Initialization frame
    }

    @Test public void framesManagerMonitorsFramesProgress() {
        fakeTimestampProvider.add(TIME_PER_FRAME);
        framesManager.frame();
        Assert.assertEquals(1, framesManager.getFramesCount());
    }

    @Test public void makeSureFramesManagerIsAwareOfFramesPassed() {
        for (int i = 0; i < 3; ++i) {
            fakeTimestampProvider.add(TIME_PER_FRAME);
            framesManager.frame();
        }
        Assert.assertEquals(3, framesManager.getFramesCount());
    }

    @Test public void resetsFramesCountAfterOneSecond() throws Exception {
        for (int i = 0; i < 3; ++i) {
            fakeTimestampProvider.add(TIME_PER_FRAME);
            framesManager.frame();
        }
        fakeTimestampProvider.add(1001);
        framesManager.frame();
        Assert.assertEquals(1, framesManager.getFramesCount());
    }

    @Test public void resetsFramesCountsAfterOneSecondRepeatedly() {
        for (int i = 0; i < 8; ++i) {
            fakeTimestampProvider.add(300);
            framesManager.frame();
        }
        Assert.assertEquals(1, framesManager.getFramesCount());
    }

    @Test public void showsFramesPerSecond() throws Exception {
        for (int i = 0; i < 3; ++i) {
            fakeTimestampProvider.add(TIME_PER_FRAME);
            framesManager.frame();
        }
        fakeTimestampProvider.add(1001);
        framesManager.frame();
        Assert.assertEquals(3, framesManager.fps());
    }

    @Test public void showsCorrectFramesPerSecond() throws Exception {
        for (int i = 0; i < 3; ++i) {
            fakeTimestampProvider.add(TIME_PER_FRAME);
            framesManager.frame();
        }
        fakeTimestampProvider.add(1001);
        for (int i = 0; i < 2; ++i) {
            fakeTimestampProvider.add(TIME_PER_FRAME);
            framesManager.frame();
        }
        fakeTimestampProvider.add(1001);
        framesManager.frame();
        Assert.assertEquals(2, framesManager.fps());
    }

    @Test public void frameShouldBeSkipped() {
        fakeTimestampProvider.add(1);
        framesManager.frame();
        Assert.assertEquals(false, framesManager.canGo());
    }

    @Test public void frameCanBeDrawn() throws Exception {
        fakeTimestampProvider.add(100);
        framesManager.frame();
        Assert.assertEquals(true, framesManager.canGo());
    }

    @Test public void doubleCheckIfFrameCanBeDrawn() throws Exception {
        for (int i = 0; i < 2; ++i) {
            fakeTimestampProvider.add(20);
            framesManager.frame();
        }
        Assert.assertEquals(true, framesManager.canGo());
    }

    @Test public void doesNotLooseTimeForTimeFrame() {
        for (int i = 0; i < 3; ++i) {
            fakeTimestampProvider.add(TIME_PER_FRAME / 2 + TIME_PER_FRAME / 3);
            framesManager.frame();
        }
        Assert.assertEquals(2, framesManager.getFramesCount());
    }
}