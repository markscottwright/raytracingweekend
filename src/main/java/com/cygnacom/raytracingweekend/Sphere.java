package com.cygnacom.raytracingweekend;

import java.util.Optional;

public class Sphere implements Hittable {
    private final Vec3 center;
    private final double radius;

    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Vec3 getCenter() {
        return center;
    }

    @Override
    public Optional<HitRecord> hit(Ray r, double timeMin, double timeMax) {
        var oc = r.getOrigin().minus(center);
        var a = r.getDirection().dot(r.getDirection());
        var b = oc.dot(r.getDirection());
        var c = oc.dot(oc) - radius * radius;
        var discriminant = b * b - a * c;

        // didn't hit
        if (discriminant < 0)
            return Optional.empty();

        // didn't hit in time range
        var hitTime = (-b - Math.sqrt(b * b - a * c)) / a;
        if (hitTime >= timeMax || hitTime <= timeMin) {
            hitTime = (-b + Math.sqrt(b * b - a * c)) / a;
            if (hitTime >= timeMax || hitTime <= timeMin) {
                return Optional.empty();
            }
        }

        // return details of hit
        var hitPoint = r.pointAtTime(hitTime);
        var hitRecord = new HitRecord(hitTime, hitPoint,
                hitPoint.minus(center).div(radius));
        return Optional.of(hitRecord);
    }
}
