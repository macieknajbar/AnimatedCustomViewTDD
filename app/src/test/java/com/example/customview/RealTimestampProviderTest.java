package com.example.customview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RealTimestampProviderTest {

    private RealTimestampProvider realTimestampProvider;

    @Before public void setUp() {
        realTimestampProvider = new RealTimestampProvider();
    }

    @Test public void providerGivesUsCurrentTime() {
        long now = System.currentTimeMillis();
        Assert.assertTrue("Timestamp fits 2ms window", realTimestampProvider.timestamp() - now < TimeUnit.MILLISECONDS.toMillis(2));
    }
}