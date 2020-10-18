package org.track.opencv;

import org.junit.Test;
import org.track.DanielTrackApplicationTests;
import org.track.core.common.opencv.Image;
import org.track.core.common.opencv.ImageOPenCvBot;

import java.io.IOException;
import java.util.List;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className imageOpencvTest
 * @description imageOpencvTest
 * @date 2020/10/18 4:17 PM
 **/
public class imageOpencvTest extends DanielTrackApplicationTests {

    @Test
    public void testOpencv() throws IOException {
        List<Image> images = ImageOPenCvBot.createObj("src/main/resources/url.csv");
        ImageOPenCvBot.perceptualHashSimilarity(images, "src/main/resources/url_diff.csv");
    }

}
