package org.track.javassist;

import javassist.util.proxy.ProxyFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

public class JavassistTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(JavassistTests.class);

    @Test
    public void testProxy() throws IllegalAccessException, InstantiationException {
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(Loop.class);
        factory.setFilter(method -> {
            if (method.getName().equals("loop")) {
                return true;
            }
            return false;
        });

        factory.setHandler((o, thisMethod, proceed, args) -> {
            long start = System.currentTimeMillis();
            Object result = proceed.invoke(o, args);
            logger.info("time is:{} ms", System.currentTimeMillis() - start);
            return result;
        });
        Class cls = factory.createClass();
        Loop o = (Loop) cls.newInstance();
        o.loop();
    }

}
