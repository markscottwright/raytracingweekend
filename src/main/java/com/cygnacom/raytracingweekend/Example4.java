package com.cygnacom.raytracingweekend;

import static com.cygnacom.raytracingweekend.Colors.LIGHT_BLUE;
import static com.cygnacom.raytracingweekend.Colors.RED;
import static com.cygnacom.raytracingweekend.Colors.WHITE;

public class Example4 extends RayTracer {
    final Hittable s = new Sphere(new Vec3(0, 0, -1), .5);

    public static void main(String[] args) {
        PpmViewer.view(new Example4().trace());
    }

    private Vec3 color(Ray r, Hittable s) {
        if (s.hit(r, 0, Double.MAX_VALUE).isPresent()) {
            return RED;
        } else {
            double gradientRatio = r.getDirection().to01Vector().getY();
            return WHITE.mul(1.0 - gradientRatio)
                    .plus(LIGHT_BLUE.mul(gradientRatio));
        }
    }

    @Override
    protected int rbgFor(Ray r) {
        return color(r, s).toRgb();
    }
}
