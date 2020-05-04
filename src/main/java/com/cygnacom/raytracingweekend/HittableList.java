package com.cygnacom.raytracingweekend;

import java.util.ArrayList;
import java.util.Optional;

public class HittableList implements Hittable {
    ArrayList<Hittable> items = new ArrayList<>();

    public HittableList(Hittable... items) {
        for (Hittable h : items)
            this.items.add(h);
    }

    @Override
    public Optional<HitRecord> hit(Ray r, double timeMin, double timeMax) {
        Optional<HitRecord> firstHit = items.stream()
                .map(o -> o.hit(r, timeMin, timeMax)).flatMap(Optional::stream)
                .min((o1, o2) -> Double.compare(o1.time, o2.time));
        return firstHit;
    }
}
