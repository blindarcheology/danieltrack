package org.track.javassist;

import javassist.CannotCompileException;
import javassist.Modifier;
import javassist.util.proxy.ProxyFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;
import org.track.core.common.javassist.DefaultCreateClass;
import org.track.core.common.javassist.Field;

import java.io.IOException;
import java.util.Collections;

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

    @Test
    public void testCreateClass() throws CannotCompileException, IOException {
        Field field = new Field("java.lang.String", "name", Modifier.PRIVATE);
        DefaultCreateClass.create("org.track.core.common.javassist.Class", Collections.singletonList(field)
                , getClass().getResource("").getPath());
    }

}
