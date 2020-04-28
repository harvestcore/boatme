package com.agm.boatme;

import java.util.ArrayList;

public class MapManager {
    private static MapManager mapManager;

    ArrayList<InterestPoint> route;

    private MapManager() {
        route = new ArrayList<>();
    }

    public static MapManager getInstance() {
        if (mapManager == null) {
            mapManager = new MapManager();
        }

        return mapManager;
    }

    public void addToRoute(float lat, float lng, String name, boolean isPort) {
        route.add(new InterestPoint(lat, lng, name, isPort));
    }

    public void removeFromRoute(final String name) {
        for (InterestPoint p: route) {
            if (p.name == name) {
                route.remove(p);
            }
        }
    }

    public ArrayList<InterestPoint> getRoute() {
        return route;
    }
}
