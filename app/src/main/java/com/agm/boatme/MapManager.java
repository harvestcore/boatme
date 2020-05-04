package com.agm.boatme;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;


public class MapManager {

    private static final String MARKER_IMAGE = "map-marker";
    private static final String MARKER_SOURCE = "markers-source";
    private static final String MARKER_STYLE_LAYER = "markers-style-layer";

    private static MapManager mapManager;

    ArrayList<InterestPoint> route;
    InterestPoint currentSelectedFromList;
    InterestPoint currentPosition;
    GeoJsonSource currentSource;
    SymbolLayer currentLayer;
    Style map;
    String currentStyle = Style.MAPBOX_STREETS;

    private MapManager() {
        route = new ArrayList<>();
        this.addToRoute(23.4582348f, 37.5062675f, "t1", false);
        this.addToRoute(23.4577141f, 37.5066109f, "t2", true);
        this.addToRoute(23.4572601f, 37.5070038f, "t3", false);
        this.addToRoute(23.4566746f, 37.507301f, "t4", false);
        this.addToRoute(23.455698f, 37.5076256f, "t5", true);
        this.addToRoute(23.4549737f, 37.5079214f, "t6", true);
        this.addToRoute(23.4545445f, 37.5080235f, "t7", false);
        this.addToRoute(23.4538579f, 37.5078873f, "t8", false);
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
        route.add(new InterestPoint(lat, lng, name, isPort));
    }

    public void removeFromRoute(final String name) {
        for (InterestPoint p: route) {
            if (p.name == name) {
                route.remove(p);
            }
        }
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
    }

    public void removeFromRoute(int position) {
        route.remove(position);
    }

    public ArrayList<InterestPoint> getRoute() {
        return route;
    }

    public void selectFromList(int position) {
        currentSelectedFromList = route.get(position);
    }

    public void deselectCurrentFromList() {
        currentSelectedFromList = null;
    }
}
