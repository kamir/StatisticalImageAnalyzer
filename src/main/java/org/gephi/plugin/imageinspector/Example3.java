package org.gephi.plugin.imageinspector;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.process.ImageProcessor;
import ij.process.StackProcessor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamir
 *
 * http://www.gingercart.com/Home/java-snippets/create-image-thumbnail-in-java-using-imagej-api
 *
 */
public class Example3 {
    
    private static final Logger logger = Logger.getLogger( Example3.class.getName() );

    public static void run() {

        String input = "data/in/stack/";
 
        File f = new File( input );
        File[] inf = f.listFiles();
        
        for( File fi : inf ) {
            
            cropAndResize( input, fi.getName(), "_mini" );
    
        }
        
    }
    
    private static String cropAndResize(String folder, String name, String fileSaveAsNameSuffix) {
        try {
            
            Opener opener = new Opener();
            
            ImagePlus imp = opener.openTiff( folder, "/" + name );
            
            ImageProcessor ip = imp.getProcessor();
            
            StackProcessor sp = new StackProcessor(imp.getStack(), ip);
            
            int width = imp.getWidth();
            int height = imp.getHeight();
            
            int cropWidth = 0;
            int cropHeight = 0;
            
            if(width > height) {
                cropWidth = height;
                cropHeight = height;
            } else {
                cropWidth = width;
                cropHeight = width;
            }
            
            int x = -1;
            int y = -1;
            
            if(width == height) {
                x = 0;
                y = 0;
            } else if(width > height) {
                x = (width - height) / 2;
                y=0;
            } else if (width < height) {
                x = 0;
                y = (height - width) / 2;
            }
              
            logger.log( Level.INFO, "w="+imp.getWidth());
            logger.log( Level.INFO, "h="+ imp.getHeight());
            logger.log( Level.INFO, "cropWidth " + cropWidth);
            logger.log( Level.INFO, "cropHeight" + cropHeight);
            
            ImageStack croppedStack = sp.crop(x, y, cropWidth, cropHeight);
            
            imp.setStack(null, croppedStack);
            
            logger.log( Level.INFO, "w=" + imp.getWidth());
            logger.log( Level.INFO, "h=" + imp.getHeight());
            
            sp = new StackProcessor(imp.getStack(), imp.getProcessor());
            
            ImageStack resizedStack = sp.resize(100, 100, true);
            imp.setStack(null, resizedStack);
            
            StringBuffer filePath = new StringBuffer(folder + "/" + name );
            
            filePath.replace(filePath.lastIndexOf("."),
            filePath.lastIndexOf("."), fileSaveAsNameSuffix);
            String saveAsFilePath = filePath.toString();
            IJ.save(imp, saveAsFilePath);
            return saveAsFilePath;
        } catch (Exception e) {
            logger.log( Level.FINE, "Error while resizing Image.");
            e.printStackTrace();
            return null;
        }
}


}
