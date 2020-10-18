package org.track.core.common.opencv;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className ImageOPenCvBot
 * @description ImageOPenCvBot
 * @date 2020/10/18 4:03 PM
 **/
public class ImageOPenCvBot {

    public static void perceptualHashSimilarity(List<Image> images, String path) throws IOException {
        List<Image> diffs = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            if (!images.get(i).getOpeningUrl().startsWith("open-url") && !images.get(i).getSecondurl().startsWith("second-url")) {
                BufferedImage openingUrl = ImageIO.read(new URL(images.get(i).getOpeningUrl()));
                BufferedImage secondUrl = ImageIO.read(new URL(images.get(i).getSecondurl()));
                String code1 = perceptualHashSimilarity(openingUrl);
                String code2 = perceptualHashSimilarity(secondUrl);

                char[] openingCh = code1.toCharArray();
                char[] secondCh = code2.toCharArray();
                int diffCount = 0;
                for (int j = 0; j < 64; j++) {
                    if (openingCh[j] != secondCh[j]) {
                        diffCount++;
                    }
                }
                diffs.add(new Image(images.get(i).getId(), diffCount));

            }
        }
        writeCsv(path, diffs);
    }

    public static List<Image> createObj(String path) throws IOException {
        List<Image> images = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] datas = line.split(",");
            Image image = new Image();
            image.setId(datas[0]);
            image.setOpeningUrl(datas[1]);
            image.setSecondurl(datas[2]);

            images.add(image);
        }
        return images;
    }

    private static void writeCsv(String path, List<Image> images) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
        for (int i = 0; i < images.size(); i++) {
            writer.write(images.get(i).getId());
            writer.write(",");
            writer.write(String.valueOf(images.get(i).getDiff()));
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    private static String perceptualHashSimilarity(BufferedImage src) {
        int width = 8;
        int height = 8;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(src, 0, 0, 8, 8, null);
        int total = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = image.getRGB(j, i);
                int gray = gray(pixel);
                total = total + gray;
            }
        }
        StringBuffer res = new StringBuffer();
        int grayAvg = total / (width * height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = image.getRGB(j, i);
                int gray = gray(pixel);
                if (gray >= grayAvg) {
                    res.append("1");
                } else {
                    res.append("0");
                }
            }
        }
        return res.toString();
    }

    private static int gray(int rgb) {
        int a = rgb & 0xff000000;
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        rgb = (r * 77 + g * 151 + b * 28) >> 8;
        return a | (rgb << 16) | (rgb << 8) | rgb;
    }
}
