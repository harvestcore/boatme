package com.agm.boatme;

public class RouteManager {
    private static RouteManager routeManager;

    private RouteManager() {}

    public static RouteManager getInstance() {
        if (routeManager == null) {
            routeManager = new RouteManager();
        }

        return routeManager;
    }

}
