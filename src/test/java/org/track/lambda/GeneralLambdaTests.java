package org.track.lambda;

import javafx.fxml.Initializable;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GeneralLambdaTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(GeneralLambdaTests.class);

    @Test
    public void testMathOperation() {
        GeneralLambdaTests tests = new GeneralLambdaTests();
        MathOperation operator = (int a, int b) -> a + b;
        int operate = tests.operate(1, 2, operator);
        logger.info("operation result is:", operate);
    }

    @Test
    public void testCollectionSort() {
        List<String> strings = Arrays.asList("A", "B", "C", "D", "a", "b", "c", "d");
        Collections.sort(strings, String::compareTo);
        logger.info("strings is:{}", strings);

        List<Integer> integers = Arrays.asList(1, 25, 3, 57);
        Collections.sort(integers, Comparator.naturalOrder());
        logger.info("integers is:{}", integers);
    }

    @Test
    public void testArbitrary() throws IOException {
        Initializable in = (url, resourceBundle) -> {
            logger.info("url is:{}.& resourceBundle is:{}", url, null);
        };
        URL url = new URL("http://www.baidu.com");
        in.initialize(url, null);
    }


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

    interface MathOperation {
        int operation(int a, int b);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
