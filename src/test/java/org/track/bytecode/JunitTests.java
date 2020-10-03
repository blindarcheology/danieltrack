package org.track.bytecode;

import org.junit.Assert;
import org.junit.Test;
import org.track.DanielTrackApplicationTests;

public class JunitTests extends DanielTrackApplicationTests {

    @Test
    public void testUnit() {
        long v = 1L;
        Assert.assertEquals(1, v);
    }

}
