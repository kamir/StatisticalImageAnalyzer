package org.github.kamir.imageinspector;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.measure.Measurements;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;
import ij.process.ImageStatistics;

import java.io.File;

/**
 *
 * @author kamir
 * 
 * http://www.gingercart.com/Home/java-snippets/create-image-thumbnail-in-java-using-imagej-api
 * 
 */
public class Example4 {
    
    public static void run(){

        File folder = new File( "/GITHUB/StatisticalImageAnalyzer/data/in/segments");

        for ( File f : folder.listFiles() ) {

            String name = f.getName();

            ImagePlus imgPlus = new ImagePlus( f.getAbsolutePath() );
            ImageProcessor imgProcessor = imgPlus.getProcessor();

            imgProcessor.invert();

            imgProcessor.flipHorizontal();
            imgProcessor.flipVertical();

            FileSaver fs1 = new FileSaver(imgPlus);
            fs1.saveAsJpeg("/GITHUB/StatisticalImageAnalyzer/data/out/segments/" + name + "__INVERTED_FLIPPED.jpg");

        }
    }

    /**
     * Normalize an image so it have 0 mean and unit variance
     * @param ip input image
     * @return normalized image (32-bit)
     */
    private static FloatProcessor normalize(ImageProcessor ip)
    {
        // get mean and standard deviation of input image
        ImageStatistics stats = ImageStatistics.getStatistics( ip, Measurements.MEAN + Measurements.STD_DEV, null);

        FloatProcessor fp = (FloatProcessor) ip.convertToFloat();

        // subtract mean
        fp.subtract(stats.mean);

        // divide by std dev
        fp.multiply(1.0 / stats.stdDev);
        return fp;

    }
}
