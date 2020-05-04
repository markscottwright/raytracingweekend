package com.cygnacom.raytracingweekend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PpmExampleWithVec3 {
    public static void main(String[] args) throws FileNotFoundException {
        File ppmFile = new File("example2.ppm");
        try (PrintStream out = new PrintStream(ppmFile)) {
            int nx = 200;
            int ny = 100;
            out.println("P3");
            out.println(nx + " " + ny);
            out.println(255);
            for (int j = ny - 1; j >= 0; --j) {
                for (int i = 0; i < nx; ++i) {
                    var v = new Vec3((double) i / (double) nx,
                            (double) j / (double) ny, 0.2);
                    int ir = (int) (255.99 * v.getR());
                    int ig = (int) (255.99 * v.getG());
                    int ib = (int) (255.99 * v.getB());
                    out.println(ir + " " + ig + "  " + ib);
                }
            }
        }
        System.out.println(ppmFile.getAbsolutePath());
    }
}
