package com.cygnacom.raytracingweekend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PpmExample {
    public static void main(String[] args) throws FileNotFoundException {
        File ppmFile = new File("example.ppm");
        try (PrintStream out = new PrintStream(ppmFile)) {
            int nx = 200;
            int ny = 100;
            out.println("P3");
            out.println(nx + " " + ny);
            out.println(255);
            for (int j = ny - 1; j >= 0; --j) {
                for (int i = 0; i < nx; ++i) {
                    var r = (float) i / (float) nx;
                    var g = (float) j / (float) ny;
                    var b = 0.2f;
                    int ir = (int) (255.99 * r);
                    int ig = (int) (255.99 * g);
                    int ib = (int) (255.99 * b);
                    out.println(ir + " " + ig + "  " + ib);
                }
            }
        }
        System.out.println(ppmFile.getAbsolutePath());
    }
}
