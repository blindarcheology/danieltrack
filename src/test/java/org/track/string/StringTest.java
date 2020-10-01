package org.track.string;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

public class StringTest extends DanielTrackApplicationTests {

    public static final Logger LOGGER = LoggerFactory.getLogger(StringTest.class);

    @Test
    public void testStringJoin() {
        String s = new String("string is");
        String join = String.join("-", s, "test", "not null");
        LOGGER.info("string join result is:{}",join);
    }

    @Test
    public void testStringFormat(){
        String s = String.format("%032d", 1);
        LOGGER.info("string format is:{}",s);
    }



}
