package com.agm.boatme;

public class InterestPoint {
    float position[];
    String name;
    boolean isPort;

    public InterestPoint(float lat, float lng, String name, boolean isPort) {
        this.position = new float[2];
        this.position[0] = lat;
        this.position[1] = lng;
        this.name = name;
        this.isPort = isPort;
    }

    public float getLat() {
        return this.position[0];
    }

    public float getLng() {
        return this.position[1] ;
    }

    public float[] getPosition() {
        return this.position;
    }

    public String getName() {
        return name;
    }

    public boolean isPort() {
        return isPort;
    }
}
