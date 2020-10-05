package org.track.lambda;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(LambdaTests.class);

    @Test
    public void testLConsumer() {
        List<String> strings = Arrays.asList("A", "B", "C", "D");
        strings.forEach(v -> logger.info(v));
    }

    @Test
    public void testSupplier() {
        Supplier<Double> random = Math::random;
        logger.info("random is {}", random.get());
    }

    @Test
    public void testPredicate() {
        List<User> users = Arrays.asList(new User(1, "1"), new User(2, "2"), new User(3, "3"));
        users.forEach(u -> {
            u.printIf(o -> logger.info("user is:{}", o), o -> o.id > 1);
        });
    }

    @Test
    public void testFunctionInterface() {
        List<User> users = Arrays.asList(new User(1, "1"), new User(2, "2"), new User(3, "3"));
        users.forEach(u -> {
            u.printIf(o -> {
                UserService consumer = (user) -> logger.info("user info is:{}", user);
                consumer.get(o);
            }, o -> o.id > 1);
        });
    }

    class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void printIf(Consumer<User> consumer, Predicate<User> predicate) {
            if (predicate.test(this)) {
                consumer.accept(this);
            }
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @FunctionalInterface
    interface UserService<T> {
        void get(T t);
    }
}
