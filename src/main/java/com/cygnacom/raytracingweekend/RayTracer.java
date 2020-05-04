package com.cygnacom.raytracingweekend;

import java.awt.image.BufferedImage;

public abstract class RayTracer {
    final int pixelsPerUnit = 250;
    final Vec3 lowerLeftCorner = new Vec3(-2, -1, -1);
    final Vec3 horizontal = new Vec3(4, 0, 0);
    final Vec3 vertical = new Vec3(0, 2, 0);
    final Vec3 origin = new Vec3(0, 0, 0);

    protected abstract int rbgFor(Ray r);

    public BufferedImage trace() {
        final int nx = (int) horizontal.getX() * pixelsPerUnit;
        final int ny = (int) vertical.getY() * pixelsPerUnit;

        BufferedImage img = new BufferedImage(nx, ny,
                BufferedImage.TYPE_INT_RGB);

        // shoot ray from origin to each pixel in output image and get the color
        for (int y = ny - 1; y >= 0; --y) {
            for (int x = 0; x < nx; ++x) {
                var xRatio = (double) x / (double) nx;
                var yRatio = (double) y / (double) ny;
                //@formatter:off
                var r = new Ray(
                        origin, 
                        lowerLeftCorner
                            .plus(horizontal.mul(xRatio))
                            .plus(vertical.mul(yRatio)));
                //@formatter:on
                img.setRGB(x, (ny-y-1), rbgFor(r));
            }
        }
        return img;
    }
}
