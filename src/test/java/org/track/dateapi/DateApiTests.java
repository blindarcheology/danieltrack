package org.track.dateapi;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DateApiTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(DateApiTests.class);

    @Test
    public void testLocalDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = now.toLocalDate();

        logger.info("now is:{}", localDate);
    }

    @Test
    public void testChronoUnit() {
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plus(1, ChronoUnit.DAYS);
        logger.info("now is:{}", localDate);
    }

    @Test
    public void testAdjusters() {
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        logger.info("DayOfWeek.FRIDAY is:{}", localDate);
    }

}
