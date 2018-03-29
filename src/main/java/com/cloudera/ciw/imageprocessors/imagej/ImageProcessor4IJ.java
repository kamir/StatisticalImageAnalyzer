package com.cloudera.ciw.imageprocessors.imagej;

import com.cloudera.ciw.imageprocessors.CIWImageProcessor;

import java.util.Properties;

/**
 * Created by kamir on 28.03.18.
 */
public abstract class ImageProcessor4IJ extends CIWImageProcessor {

    boolean debug = false;

    /**
     * This is the main function we have to implement for Image-to-Doc processing.
     *
     * @param imageAsBASE64
     * @param title
     * @return
     */
    abstract public Properties processPNG_as_BASE64String_to_SolrDoc(String imageAsBASE64 , String title );

    abstract public Properties processPNG_as_BASE64String_to_SolrDoc(String imageAsBASE64 , String title, Properties mdFields );
}
