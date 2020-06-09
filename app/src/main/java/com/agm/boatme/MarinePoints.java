package com.agm.boatme;

import java.util.ArrayList;

public class MarinePoints {
    private static MarinePoints marinePoints;
    ArrayList<InterestPoint> points;

    private MarinePoints(){
        points = new ArrayList<>();

        points.add(new InterestPoint(36.7443f, -3.0193f, "Adra", true));
        points.add(new InterestPoint(36.7564f, -2.6054f, "Roquetas", true));
        points.add(new InterestPoint(36.8290f, -2.4738f, "Almeria", true));
        points.add(new InterestPoint(36.7072f, -4.4190f, "Malaga", true));
        points.add(new InterestPoint(36.5955f, -4.5128f, "Benalmadena", true));
        points.add(new InterestPoint(36.7472f, -4.0720f, "Caleta de Velez", true));
        points.add(new InterestPoint(36.7202f, -3.5210f, "Motril", true));
        points.add(new InterestPoint(36.4859f, -4.9522f, "Marbella", true));

        points.add(new InterestPoint(36.8173f, -2.4556f, "Point-0", false));
        points.add(new InterestPoint(36.7075f, -2.5784f, "Point-1", false));
        points.add(new InterestPoint(36.6249f, -2.7068f, "Point-2", false));
        points.add(new InterestPoint(36.6756f, -3.0260f, "Point-3", false));
        points.add(new InterestPoint(36.6442f, -3.3213f, "Point-4", false));
        points.add(new InterestPoint(36.6150f, -3.6681f, "Point-5", false));
        points.add(new InterestPoint(36.6640f, -4.1267f, "Point-6", false));
        points.add(new InterestPoint(36.5869f, -4.3588f, "Point-7", false));
        points.add(new InterestPoint(36.4831f, -4.5058f, "Point-8", false));
        points.add(new InterestPoint(36.4169f, -4.8230f, "Point-9", false));
    }

    public static MarinePoints getInstance() {
        if (marinePoints == null) {
            marinePoints = new MarinePoints();
        }

        return marinePoints;
    }

    public ArrayList<InterestPoint> getPoints() {
        return points;
    }

    public InterestPoint getByName(String name) {
        for (InterestPoint p: points) {
            if (p.name.toLowerCase().equals(name.toLowerCase())) {
                return p;
            }
        }

        return null;
    }

    private float getDistance(InterestPoint a, InterestPoint b) {
        double dx = a.getLat() - b.getLat();
        double dy = a.getLng() - b.getLng();

        return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    private InterestPoint getClosest(InterestPoint point, ArrayList<InterestPoint> alreadyUsed) {
        InterestPoint closest = null;
        float distance = Float.MAX_VALUE;
        for (InterestPoint p: points) {
            float d = getDistance(p, point);
            if (d < distance && !alreadyUsed.contains(p)) {
                distance = d;
                closest = p;
            }
        }

        return closest;
    }

    public ArrayList<InterestPoint> getRoute(String portA, String portB) {
        ArrayList<InterestPoint> route = new ArrayList<>();
        InterestPoint a = this.getByName(portA);
        InterestPoint b = this.getByName(portB);

        route.add(a);

        while (!route.contains(b)) {
            route.add(this.getClosest(route.get(route.size()-1), route));
        }

        return route;
    }
}
