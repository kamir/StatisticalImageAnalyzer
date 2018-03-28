package org.github.kamir.imageinspector;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;

/**
 *
 * @author kamir
 * 
 * http://www.gingercart.com/Home/java-snippets/create-image-thumbnail-in-java-using-imagej-api
 * 
 */
public class Example1 {
    
    public static void run(){

        ImagePlus imgPlus = new ImagePlus("/GITHUB/StatisticalImageAnalyzer/data/in/fox1.jpeg");

        ImageProcessor imgProcessor = imgPlus.getProcessor();
        imgProcessor.invert();
        FileSaver fs = new FileSaver(imgPlus);
        
        fs.saveAsJpeg("/GITHUB/StatisticalImageAnalyzer/data/out/fox1-inverted.jpg");
    
    }
    
}
