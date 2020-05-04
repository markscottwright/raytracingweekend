package com.cygnacom.raytracingweekend;

import java.util.Optional;

public interface Hittable {

    public static class HitRecord {
        public HitRecord(double hitTime, Vec3 hitPoint, Vec3 normal) {
            this.time = hitTime;
            this.point = hitPoint;
            this.normal = normal;
        }

        final double time;
        final Vec3 point;
        final Vec3 normal;
    }

    Optional<HitRecord> hit(Ray r, double timeMin, double timeMax);
}