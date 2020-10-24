package org.track.core.common.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className MethodTimeConsumingMonitor
 * @description MethodTimeConsumingMonitor
 * @date 2020/10/24 5:03 PM
 **/
public class MethodTimeConsumingMonitor {

    public static final Logger logger = LoggerFactory.getLogger(MethodTimeConsumingMonitor.class);

    public static void test() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            logger.error("InterruptedException:{}", e);
        }
        System.out.println("test......");
    }

}
