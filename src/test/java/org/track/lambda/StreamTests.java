package org.track.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(StreamTests.class);

    @Test
    public void testStreamFilter() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        strings = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        strings.forEach(System.out::println);
    }

    @Test
    public void testStreamMap() {
        List<Integer> integers = Arrays.asList(1, 20, 3, 4, 5);
        integers = integers.stream().limit(3).map(o -> o * 2).sorted().collect(Collectors.toList());
        integers.forEach(System.out::println);
    }

    @Test
    public void testIntSummaryStatistics() {
        List<Integer> integers = Arrays.asList(1, 20, 3, 4, 5);
        IntSummaryStatistics summaryStatistics = integers.stream().mapToInt(o -> o * 10).summaryStatistics();
        int max = summaryStatistics.getMax();
        logger.info("max result is:{}", max);
    }
}
