package org.track.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

import java.util.ArrayList;
import java.util.List;

public class MethodQuoteTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(MethodQuoteTests.class);

    @Test
    public void testConstructor() {
        List<String> names = new ArrayList();

        names.add("Google");
        names.add("Gradle");
        names.add("Alibaba");
        names.add("Baidu");

        names.forEach(System.out::println);
    }
}
