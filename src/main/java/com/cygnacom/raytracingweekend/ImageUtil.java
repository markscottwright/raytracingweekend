package com.cygnacom.raytracingweekend;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ImageUtil {

    public static BufferedImage scale(BufferedImage image, double xRatio,
            double yRatio) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage after = new BufferedImage((int) (w * xRatio),
                (int) (h * yRatio), BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale((float) xRatio, (float) yRatio);
        AffineTransformOp scaleOp = new AffineTransformOp(at,
                AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(image, after);
    }

    public static BufferedImage readPpm(File ppmFile) throws IOException,
            FileNotFoundException {
        try (var scanner = new Scanner(ppmFile)) {
            String ppmType = scanner.next();
            if (!ppmType.equals("P3"))
                throw new IOException("Unexpected PPM type " + ppmType);
            final int width = scanner.nextInt();
            final int height = scanner.nextInt();
            final int shouldBe255 = scanner.nextInt();
            if (shouldBe255 != 255)
                throw new IOException(
                        "Unexpected PPM value " + shouldBe255 + " != 255");

            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            int x = 0;
            int y = 0;
            while (scanner.hasNext()) {
                final int r = scanner.nextInt();
                final int g = scanner.nextInt();
                final int b = scanner.nextInt();
                image.setRGB(x++, y, r * 256 * 256 + g * 256 + b);
                if (x == width) {
                    x = 0;
                    y++;
                }
            }
            return image;
        }
    }

    public static int toRgb(Ray r) {
        // TODO Auto-generated method stub
        return 0;
    }

}
