package org.github.kamir.imageinspector;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.io.Opener;
import ij.process.ImageProcessor;
import ij.process.StackProcessor;

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

    public static void run_on_TIFF(String input) {

        File f = new File( input );

        System.out.print( ">>> process TIFF images in folder: " + f.getAbsolutePath() );

        File[] inf = f.listFiles();

        System.out.println( "  (" + inf.length + ")" );

        for( File fi : inf ) {

            System.out.println( fi.getAbsolutePath() );
            cropAndResizeTIFF( input, fi.getName(), "_mini" );
    
        }
        
    }
    
    private static String cropAndResizeTIFF(String folder, String name, String fileSaveAsNameSuffix) {
        try {
            
            Opener opener = new Opener();
            
            ImagePlus imp = opener.openTiff( folder, "/" + name );

            if( imp == null ) throw new Exception("no image available in: Example3.cropAndResize ... " );
            
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
            logger.log( Level.ALL, ">>> Error while resizing TIFF Image.");
            e.printStackTrace();
            return null;
        }
}


    public static void run_on_PNG(String input) {
        File f = new File( input );

        System.out.print( ">>> process PNG images in folder: " + f.getAbsolutePath() );

        File[] inf = f.listFiles();

        System.out.println( "  (" + inf.length + ")" );

        for( File fi : inf ) {

            System.out.println( fi.getAbsolutePath() );
            cropAndResizePNG( input, fi.getName(), "_mini" );

        }
    }

    private static String cropAndResizePNG(String folder, String name, String fileSaveAsNameSuffix) {

        try {

            Opener opener = new Opener();
            // Opens, but does not display, the specified image file and returns an ImagePlus object object if successful, or returns null if the file is not in a supported format or is not found.
            // formats:   tiff, dicom, fits, pgm, jpeg, bmp, gif, lut, roi, or text file.
            ImagePlus imp = opener.openImage( folder,  "/" + name );

            if( imp == null ) throw new Exception("no image available in: Example3.cropAndResize ... {"+folder + "/" + name+"}" );

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

        }
        catch (Exception e) {
            logger.log( Level.ALL, ">>> Error while resizing PNG Image.");
            e.printStackTrace();
            return null;
        }

    }

}
