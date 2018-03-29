package com.cloudera.ciw.imageprocessors.imagej;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.measure.Measurements;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;
import ij.process.ImageStatistics;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * Created by kamir on 28.03.18.
 */
public class NormalizeProcessor extends ImageProcessor4IJ {

    public String processPNG_as_BASE64String( String imageAsBASE64 , String title) {

        BufferedImage img = base64StringToImg( imageAsBASE64 );

        ImagePlus imgPlus = new ImagePlus( title, (java.awt.Image) img );

        ImageProcessor imgProcessor = imgPlus.getProcessor();

        // imgProcessor.invert();
        // imgProcessor.flipHorizontal();
        // imgProcessor.flipVertical();

        FloatProcessor fp = normalize( imgProcessor );

        if ( debug ) {
            FileSaver fs1 = new FileSaver(imgPlus);
            fs1.saveAsJpeg("/GITHUB/StatisticalImageAnalyzer/data/out/debug_out/title_debug_output_INVERTED_FLIPPED.jpg");
        }

        // BufferedImage bimResult1 = imgProcessor.getBufferedImage();
        BufferedImage bimResult2 = fp.getBufferedImage();

        return imgToBase64String( bimResult2 );

    }

    @Override
    public Properties processPNG_as_BASE64String_to_SolrDoc(String imageAsBASE64, String title) {
        // NO SOLR RELATED DATA Gets Extracted in this Processor
        return null;
    }

    @Override
    public Properties processPNG_as_BASE64String_to_SolrDoc(String imageAsBASE64, String title, Properties mdFields) {
        return null;
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
