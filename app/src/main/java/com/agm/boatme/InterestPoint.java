package com.agm.boatme;

public class InterestPoint {
    float lat;
    float lng;
    String name;
    boolean isPort;

    public InterestPoint(float lat, float lng, String name, boolean isPort) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.isPort = isPort;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public boolean isPort() {
        return isPort;
    }
}
