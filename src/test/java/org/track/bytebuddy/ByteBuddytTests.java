package org.track.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className ByteBuddytests
 * @description ByteBuddytests
 * @date 2020/10/17 4:42 PM
 **/
public class ByteBuddytTests extends DanielTrackApplicationTests {

    public static final Logger loggger = LoggerFactory.getLogger(ByteBuddytTests.class);

    @Test
    public void create() throws IllegalAccessException, InstantiationException {
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("hello world!"))
                .make();

        Class<?> loaded = dynamicType.load(getClass().getClassLoader()).getLoaded();

        Assert.assertEquals("does not meet expectations", loaded.newInstance().toString(), "hello world!");
    }
}
