package org.track.javassist;

public class Loop {
    public void loop() {
        try {
            System.out.println("Loop.loop() invoked");
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
