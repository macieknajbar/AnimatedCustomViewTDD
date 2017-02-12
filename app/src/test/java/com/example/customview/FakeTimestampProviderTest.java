package com.example.customview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class FakeTimestampProviderTest {

    private FakeTimestampProvider fakeTimestampProvider;

    @Before public void setUp() {
        fakeTimestampProvider = new FakeTimestampProvider();
    }

    @Test public void timestampReturnsOneSecond() {
        long second = TimeUnit.SECONDS.toMillis(1);
        fakeTimestampProvider.add(second);
        Assert.assertEquals(second, fakeTimestampProvider.timestamp());
    }

    @Test public void timestampReturnsElapsedTime() {
        fakeTimestampProvider.add(1000);
        fakeTimestampProvider.add(500);
        Assert.assertEquals(1500, fakeTimestampProvider.timestamp());
    }
}