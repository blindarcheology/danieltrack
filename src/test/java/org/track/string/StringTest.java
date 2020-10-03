package org.track.string;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

public class StringTest extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(StringTest.class);

    @Test
    public void testStringJoin() {
        String s = new String("string is");
        String join = String.join("-", s, "test", "not null");
        logger.info("string join result is:{}", join);
    }

    @Test
    public void testStringFormat(){
        String s = String.format("%032d", 1);
        logger.info("string format is:{}", s);
    }

    @Test
    public void testFormatFlag(){
        final String KEY_PERFIX = "long_%s_%s_user";
        String key = String.format(KEY_PERFIX, "biz_id_123", "biz_token_123");
        logger.info("key is {}", key);
    }


}
