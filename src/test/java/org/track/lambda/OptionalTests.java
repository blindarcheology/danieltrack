package org.track.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

import java.util.Objects;
import java.util.Optional;

public class OptionalTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(OptionalTests.class);

    @Test
    public void testOptional() {
        Optional<User> user = Optional.ofNullable(new User());
        User o = user.get();
        logger.info("user is:{}", o);

        Optional<User> o1 = Optional.of(o);
        User o2 = Objects.requireNonNull(o);
    }

    class User {
    }

}
