package com.agm.boatme;

import android.graphics.Color;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;


public class MapManager {

    private static final String MARKER_IMAGE = "map-marker";
    private static final String MARKER_SOURCE = "markers-source";
    private static final String MARKER_STYLE_LAYER = "markers-style-layer";

    private static MapManager mapManager;

    boolean routeActive = false;
    ArrayList<InterestPoint> route;
    InterestPoint currentSelectedFromList;
    GeoJsonSource currentSource;
    SymbolLayer currentLayer;
    Style map;
    String currentStyle = Style.MAPBOX_STREETS;

    private MapManager() {
        route = new ArrayList<>();
        route.add(new InterestPoint(0,0, "The route is empty!", true));
    }

    public static MapManager getInstance() {
        if (mapManager == null) {
            mapManager = new MapManager();
        }

        return mapManager;
    }

    public static String getMarkerImage() {
        return MARKER_IMAGE;
    }

    public void addToRoute(float lat, float lng, String name, boolean isPort) {
        if (route.size() == 1 && route.get(0).name.contains("The route is empty!")) {
            route.clear();
        }

        route.add(new InterestPoint(lat, lng, name, isPort));
    }

    public void removeFromRoute(final String name) {
        for (InterestPoint p: route) {
            if (p.name == name) {
                route.remove(p);
            }
        }
    }

    public void setRouteActive(boolean routeActive) {
        this.routeActive = routeActive;
    }

    public void setRoute(ArrayList<InterestPoint> route) {
        this.route = route;
    }

    public void clearRoute() {
        this.route.clear();
        this.routeActive = false;
    }

    public void setMap(Style map) {
        this.map = map;
    }

    private ArrayList<Feature> transferPoints() {
        ArrayList<Feature> aux = new ArrayList<>();
        if (this.route.size() > 0) {
            for (InterestPoint r: this.route) {
                aux.add(Feature.fromGeometry(Point.fromLngLat(r.getLng(), r.getLat())));
            }
        }
        return aux;
    }

    private ArrayList<Point> getGeoLine() {
        ArrayList<Point> aux = new ArrayList<>();

        for (InterestPoint p: route) {
            aux.add(Point.fromLngLat(p.getLng(), p.getLat()));
        }

        return aux;
    }

    public String getCurrentMapStyle() {
        return currentStyle;
    }

    public void setMapStyle(String style) {
        switch (style) {
            case "Streets":
                this.currentStyle = Style.MAPBOX_STREETS;
                break;
            case "Dark":
                this.currentStyle = Style.DARK;
                break;
            case "Light":
                this.currentStyle = Style.LIGHT;
                break;
            case "Satellite":
                this.currentStyle = Style.SATELLITE;
                break;
            case "Satellite streets":
                this.currentStyle = Style.SATELLITE_STREETS;
                break;
            case "Traffic":
                this.currentStyle = Style.TRAFFIC_DAY;
                break;
            case "Traffic night":
                this.currentStyle = Style.TRAFFIC_NIGHT;
                break;
        }
    }

    public void plotPoints() {
        // POINT LAYER
        ArrayList<Feature> points = this.transferPoints();
        this.currentSource = new GeoJsonSource(MARKER_SOURCE, FeatureCollection.fromFeatures(points));
        this.map.addSource(this.currentSource);

        this.currentLayer = new SymbolLayer(MARKER_STYLE_LAYER, MARKER_SOURCE)
                .withProperties(
                        PropertyFactory.iconAllowOverlap(true),
                        PropertyFactory.iconIgnorePlacement(true),
                        PropertyFactory.iconImage(MARKER_IMAGE),
                        PropertyFactory.iconOffset(new Float[] {0f, 0f})
                );
        this.map.addLayer(this.currentLayer);


        if (this.routeActive) {
            // LINE LAYER
            this.map.addSource(new GeoJsonSource("line-source",
                    FeatureCollection.fromFeatures(new Feature[] {Feature.fromGeometry(
                            LineString.fromLngLats(this.getGeoLine())
                    )})));

            this.map.addLayer(new LineLayer("linelayer", "line-source")
                    .withProperties(
                            PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                            PropertyFactory.lineJoin(Property.LINE_JOIN_MITER),
                            PropertyFactory.lineOpacity(.7f),
                            PropertyFactory.lineWidth(3f),
                            PropertyFactory.lineColor(Color.parseColor("#ff0000"))));
        }
    }

    public void removeFromRoute(int position) {
        route.remove(position);
    }

    public ArrayList<InterestPoint> getRoute() {
        return route;
    }

    public void deselectCurrentFromList() {
        currentSelectedFromList = null;
    }
}
