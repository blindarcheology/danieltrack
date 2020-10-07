package org.track.paradigm;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.track.DanielTrackApplicationTests;

import java.util.ArrayList;
import java.util.List;

public class ParadigmTests extends DanielTrackApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(ParadigmTests.class);

    @Test
    public void testParadigm() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        test(students, students);
    }

    private static <User> void test(List<? extends User> src, List<? super User> dest) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }
}

class User {
}

class Student extends User {
}
