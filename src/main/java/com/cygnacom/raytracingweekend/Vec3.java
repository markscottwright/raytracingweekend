package com.cygnacom.raytracingweekend;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class is both a vector and a color, because what the tutorial does.
 */
public class Vec3 {
    final private double x, y, z;

    public Vec3() {
        x = y = z = 0;
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getR() {
        return x;
    }

    public double getG() {
        return y;
    }

    public double getB() {
        return z;
    }

    double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    double squaredLength() {
        return (x * x + y * y + z * z);
    }

    @Override
    public String toString() {
        return String.format("%f,%f,%f", x, y, z);
    }

    public void writeOn(PrintStream s) {
        s.printf("%f %f %f", x, y, z);
    }

    public static Vec3 readFrom(InputStream s) {
        try (Scanner parser = new Scanner(s)) {
            return new Vec3(parser.nextDouble(), parser.nextDouble(),
                    parser.nextDouble());
        }
    }

    public Vec3 toUnitVector() {
        double k = 1.0 / Math.sqrt(x * x + y * y + z * z);
        return new Vec3(x * k, y * k, z * k);
    }

    /**
     * This is the unit vector, shifted and scaled to 0-1 range
     */
    public Vec3 to01Vector() {
        var unit = toUnitVector();
        return new Vec3(unit.getX() + 1, unit.getY() + 1, unit.getZ() + 1)
                .mul(.5);

    }

    public Vec3 plus(Vec3 o) {
        return new Vec3(x + o.x, y + o.y, z + o.z);
    }

    public Vec3 minus(Vec3 o) {
        return new Vec3(x - o.x, y - o.y, z - o.z);
    }

    public Vec3 mul(Vec3 o) {
        return new Vec3(x * o.x, y * o.y, z * o.z);
    }

    public Vec3 mul(double o) {
        return new Vec3(x * o, y * o, z * o);
    }

    public double dot(Vec3 o) {
        return x * o.x + y * o.y + z * o.z;
    }

    public Vec3 div(Vec3 o) {
        return new Vec3(x / o.x, y / o.y, z / o.z);
    }

    public Vec3 cross(Vec3 o) {
        return new Vec3(y * o.z - z * o.y, -(x * o.z - z * o.x),
                x * o.y - y * o.x);
    }

    public void writeRgbOn(PrintStream out) {
        int ir = (int) (255.99 * getR());
        int ig = (int) (255.99 * getG());
        int ib = (int) (255.99 * getB());
        out.println(ir + " " + ig + "  " + ib);
    }

    /**
     * Assumes components are in 0-1 range
     */
    public int toRgb() {
        return ((int) (255.99 * getR())) * 256 * 256
                + ((int) (255.99 * getG())) * 256 + ((int) (255.9 * getB()));
    }

    public Vec3 div(double radius) {
        double k = 1 / radius;
        return new Vec3(getX() * k, getY() * k, getZ() * k);
    }
}
