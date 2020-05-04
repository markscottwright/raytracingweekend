package com.cygnacom.raytracingweekend;

import static com.cygnacom.raytracingweekend.Colors.LIGHT_BLUE;
import static com.cygnacom.raytracingweekend.Colors.WHITE;

import java.util.Optional;

import com.cygnacom.raytracingweekend.Hittable.HitRecord;

public class Example6 extends RayTracer {
    final Hittable s = new HittableList(new Sphere(new Vec3(0, 0, -1), .5),
            new Sphere(new Vec3(0, -100.5, -1), 100));

    @Override
    protected int rbgFor(Ray r) {
        Optional<HitRecord> maybeHit = s.hit(r, 0,
                Double.MAX_VALUE);
        if (maybeHit.isPresent()) {
            return maybeHit.get().normal.to01Vector().toRgb();
        } else {
            // white to light blue gradient by Y axis
            double saturationRatio = r.getDirection().to01Vector().getY();
            return WHITE.mul(1 - saturationRatio)
                    .plus(LIGHT_BLUE.mul(saturationRatio)).toRgb();
        }
    }

    public static void main(String[] args) {
        PpmViewer.view(new Example6().trace());
    }
}
