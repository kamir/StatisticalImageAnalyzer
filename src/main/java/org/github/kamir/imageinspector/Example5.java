package org.github.kamir.imageinspector;

import core.imageprocessors.CIWImageProcessor;
import core.NormalizeProcessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author kamir
 *
 *
 *
 * BACKGROUND Information for TO BASE64 encoded images:
 *
 *    https://www.davidbcalhoun.com/2011/when-to-base64-encode-images-and-when-not-to/
 *
 */
public class Example5 {
    
    public static void run() throws Exception {

        File folder = new File( "/GITHUB/StatisticalImageAnalyzer/data/in/segments");

        for ( File f : folder.listFiles() ) {

            String name = f.getName();
            BufferedImage bim = ImageIO.read( new FileInputStream( f ) );
            // This step simulates the creation of an BASE64 image string which is stored in HBase and accessible in an RDD.
            String BASE64Image_INPUT = CIWImageProcessor.imgToBase64String( bim );

            /**
             * Processing of the String via custom processor
             **/
            NormalizeProcessor p = new NormalizeProcessor();

            String resultImageAsBASE64 = p.processPNG_as_BASE64String( BASE64Image_INPUT, f.getName() );

            System.out.println( resultImageAsBASE64 );

            BufferedImage bim2 = CIWImageProcessor.base64StringToImg( resultImageAsBASE64 );
            ImageIO.write( bim2, "PNG" , new File( f.getAbsolutePath() + "_processed.png" ) );

        }
    }




}
