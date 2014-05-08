/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.html.leaflet.api;

import net.java.html.js.JavaScriptBody;

/** Class that represents one leaflet map associated with an element.
 *
 * @author Jaroslav Tulach
 */
public final class Leaflet {
    private final Object map;
    
    private Leaflet(Object map) {
        this.map = map;
    }
    
    public static Leaflet map(String id) {
        return new Leaflet(init(id));
    }
    
    public Leaflet setView(double longitude, double latitude, int zoom) {
        setViewImpl(map, longitude, latitude, zoom);
        return this;
    }
    
    public Leaflet addTileLayer(String url, String attribution, int maxZoom, String id) {
        addTileLayerImpl(map, url, attribution, maxZoom, id);
        return this;
    }
    
    @JavaScriptBody(args = { "id" }, body = "return L.map(id);")
    private static native Object init(String id);
    
    @JavaScriptBody(args = { "map", "longitude", "latitude", "zoom" }, wait4js = false, body = 
        "map.setView([longitude, latitude], zoom);"
    )
    private static native void setViewImpl(Object map, double longitude, double latitude, int zoom);

    @JavaScriptBody(args = { "map", "url", "attribution", "maxZoom", "id" }, wait4js = false, body =
        "L.tileLayer(url, {\n" +
        "  'maxZoom': 18,\n" +
        "  'attribution': attribution,\n" +
        "  'id': id\n" +
        "}).addTo(map);"
    )
    private static native void addTileLayerImpl(
        Object map, String url, String attribution, int maxZoom, String id
    );
}