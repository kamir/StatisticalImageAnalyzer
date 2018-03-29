package com.cloudera.ciw.imageprocessors;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by kamir on 28.03.18.
 */
public abstract class CIWImageProcessor {

    public static String imgToBase64String(final RenderedImage img) {

        String formatName = "PNG";

        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, formatName, Base64.getEncoder().wrap(os));
            return os.toString(StandardCharsets.ISO_8859_1.name());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }

    }

    public static BufferedImage base64StringToImg(final String base64String) {
        try {
            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    /**
     * This is the main function we have to implement for Image-to-Image processing.
     *
     * @param imageAsBASE64
     * @param title
     * @return
     */
    abstract public String processPNG_as_BASE64String( String imageAsBASE64 , String title);


}
