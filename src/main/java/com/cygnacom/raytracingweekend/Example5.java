package com.cygnacom.raytracingweekend;

import static com.cygnacom.raytracingweekend.Colors.LIGHT_BLUE;
import static com.cygnacom.raytracingweekend.Colors.WHITE;

import java.util.Optional;

import com.cygnacom.raytracingweekend.Hittable.HitRecord;

public class Example5 extends RayTracer {
    final Sphere s = new Sphere(new Vec3(0, 0, -1), .5);

    @Override
    protected int rbgFor(Ray r) {
        Optional<HitRecord> maybeHit = s.hit(r, Double.MIN_VALUE,
                Double.MAX_VALUE);
        if (maybeHit.isPresent()) {
            return maybeHit.get().point.minus(s.getCenter()).to01Vector()
                    .toRgb();
        } else {
            // white to light blue gradient by Y axis
            double saturationRatio = r.getDirection().to01Vector().getY();
            return WHITE.mul(1 - saturationRatio)
                    .plus(LIGHT_BLUE.mul(saturationRatio)).toRgb();
        }
    }

    public static void main(String[] args) {
        PpmViewer.view(new Example5().trace());
    }
}
