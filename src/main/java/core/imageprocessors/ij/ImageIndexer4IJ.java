package core.imageprocessors.ij;

import core.imageprocessors.CIWImageIndexer;

import java.util.Properties;

/**
 * Created by kamir on 28.03.18.
 */
public class ImageIndexer4IJ extends CIWImageIndexer {


    @Override
    public Properties processPNG_as_BASE64String_to_SolrDoc(String imageAsBASE64, String title) {
        return null;
    }

    @Override
    public Properties processPNG_as_BASE64String_to_SolrDoc(String imageAsBASE64, String title, Properties mdFields) {
        return null;
    }

    @Override
    public String processPNG_as_BASE64String(String imageAsBASE64, String title) {
        return null;
    }
}
