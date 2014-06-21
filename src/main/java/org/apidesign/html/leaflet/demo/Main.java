/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2014 Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.apidesign.html.leaflet.demo;

import net.java.html.boot.BrowserBuilder;
import org.apidesign.html.leaflet.api.LatLng;
import org.apidesign.html.leaflet.api.Leaflet;
import org.apidesign.html.leaflet.api.MouseEvent;
import org.apidesign.html.leaflet.api.MouseListener;


/** Bootstrap and initialization. */
public final class Main {
    private Main() {
    }
    
    /** Launches the browser */
    public static void main(String... args) throws Exception {
        BrowserBuilder.newBrowser().
            loadPage("pages/index.html").
            loadClass(Main.class).
            invoke("onPageLoad", args).
            showAndWait();
        System.exit(0);
    }
    
    /** Called when page is ready */
    public static void onPageLoad(String... args) throws Exception {
        final Leaflet map = Leaflet.map("map");
        map.setView(new LatLng(51.505, -0.09), 13);
        map.addTileLayer(
            "https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png",
            "Map data &copy; <a href='http://openstreetmap.org'>OpenStreetMap</a> contributors, " +
            "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, " +
            "Imagery © <a href='http://mapbox.com'>Mapbox</a>",
            18,
            "jtulach.iimpdmak"
        );
        map.addCircle(
            new LatLng(51.508, -0.11), 500, "red", "#f03", 0.5
        ).bindPopup("I am a circle");
        map.addPolygon(
            new LatLng(51.509, -0.08),
            new LatLng(51.503, -0.06),
            new LatLng(51.51, -0.047) 
        ).bindPopup("I am a polygon");

        map.on(MouseEvent.Type.CLICK, new MouseListener() {
            @Override
            public void onEvent(MouseEvent ev) {
                map.openPopup(ev.getLatLng(), "You clicked the map at " + ev.getLatLng());
            }
        });
    }
}
