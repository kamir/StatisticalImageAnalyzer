package core.imageprocessors;

import java.util.Properties;

/**
 * Created by kamir on 28.03.18.
 */
public abstract class CIWImageIndexer extends CIWImageProcessor {

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
