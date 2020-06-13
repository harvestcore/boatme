package com.agm.boatme;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MarinePoints {
    private static MarinePoints marinePoints;
    ArrayList<InterestPoint> points;

    private MarinePoints(){
        points = new ArrayList<>();
    }

    public static MarinePoints getInstance() {
        if (marinePoints == null) {
            marinePoints = new MarinePoints();
        }

        return marinePoints;
    }

    public void parseData(XmlResourceParser parser) {
        int eventType = -1;
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                String pointValue = parser.getName();
                if (pointValue.equals("point")) {
                    String lat = parser.getAttributeValue(null, "lat");
                    String lng = parser.getAttributeValue(null, "lng");
                    String name = parser.getAttributeValue(null, "name");
                    String isPort = parser.getAttributeValue(null, "isPort");

                    this.points.add(new InterestPoint(
                            Float.parseFloat(lat),
                            Float.parseFloat(lng),
                            name,
                            isPort.equals("true")
                    ));
                }
            }

            try {
                eventType = parser.next();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
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
