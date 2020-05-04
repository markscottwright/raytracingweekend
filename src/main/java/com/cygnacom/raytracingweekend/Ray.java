package com.cygnacom.raytracingweekend;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Ray {
    private final Vec3 origin;
    private final Vec3 direction;

    public Vec3 getDirection() {
        return direction;
    }

    public Vec3 getOrigin() {
        return origin;
    }

    public Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d) -> ()", origin.getX(), origin.getY(),
                origin.getZ(), direction.getX(), direction.getY(),
                direction.getZ());
    }

    public static void main(String[] args) throws IOException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException {
        final int nx = 200;
        final int ny = 100;
        final var lowerLeftCorner = new Vec3(-2, -1, -1);
        final var horizontal = new Vec3(4, 0, 0);
        final var vertical = new Vec3(0, 2, 0);
        final var origin = new Vec3(0, 0, 0);

        File ppmFile = new File("example3.ppm");
        try (PrintStream out = new PrintStream(ppmFile)) {
            out.println("P3");
            out.println(nx + " " + ny);
            out.println(255);
            for (int j = ny - 1; j >= 0; --j) {
                for (int i = 0; i < nx; ++i) {
                    double u = (double) i / (double) nx;
                    double v = (double) j / (double) ny;
                    var r = new Ray(origin, lowerLeftCorner
                            .plus(horizontal.mul(u)).plus(vertical.mul(v)));
                    Vec3 c = color(r);
                    c.writeRgbOn(out);
                }
            }
        }
        System.out.println(ppmFile.getAbsolutePath());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        PpmViewer.view(ppmFile);
    }

    private static Vec3 color(Ray r) {
        var unitDirection = r.getDirection().toUnitVector();
        double t = 0.5 * (unitDirection.getY() + 1);
        return new Vec3(1, 1, 1).mul(1.0 - t).plus(new Vec3(.5, .7, 1).mul(t));
    }

    public Vec3 pointAtTime(double time) {
        return origin.plus(direction.mul(time));
    }
}
