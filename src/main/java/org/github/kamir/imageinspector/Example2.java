package org.github.kamir.imageinspector;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author kamir
 *
 * http://www.gingercart.com/Home/java-snippets/create-image-thumbnail-in-java-using-imagej-api
 *
 */
public class Example2 {

    public static void run() {

        ImagePlus imgPlus = new ImagePlus("/GITHUB/StatisticalImageAnalyzer/data/in/sample1.tiff");

        ImageProcessor imgProcessor = imgPlus.getProcessor();

        BufferedImage bufferedImage = imgProcessor.getBufferedImage();
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                int grayLevel = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                int r = grayLevel;
                int g = grayLevel;
                int b = grayLevel;
                int rgb = (r << 16) | (g << 8) | b;
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        ImagePlus grayImg = new ImagePlus("gray", bufferedImage);
        FileSaver fs = new FileSaver(grayImg);
        fs.saveAsJpeg("/GITHUB/StatisticalImageAnalyzer/data/out/sample1-gray.jpg");

    }

}
