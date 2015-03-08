/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015
 * Andreas Grimmer <a.grimmer@gmx.at>
 * Christoph Sperl <ch.sperl@gmx.at>
 * Stefan Wurzinger <swurzinger@gmx.at>
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
package org.apidesign.html.leaflet.api;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;

/**
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class Popup  {
    
    private final Object jsObj;
    
    Object getJSObj() {
        return jsObj;
    }
    
    public Popup() {
        this(new PopupOptions());
    }
    
    public Popup (PopupOptions options) {
        this.jsObj = create1Internal(options);
    }
    
    public Popup (PopupOptions options, ILayer source) {
        this.jsObj = create2Internal(options, source.getJSObj());
    }
    
    Popup(Object jsObj) {
        this.jsObj = jsObj;
    }
    
    @JavaScriptBody(args = { "options" }, 
            body = "return L.Popup(options);")
    private static native Object create1Internal(Object options);
    
    @JavaScriptBody(args = { "options", "source" }, 
            body = "return L.Popup(options, source);")
    private static native Object create2Internal(Object options, Object source);
    
    
    
    // ------ Methods -----------------------------------------
    
    public void addTo(Map map) {
        addToInternal(jsObj, map.getJSObj());
    }
    
    public void openOn(Map map) {
        openOnInternal(jsObj, map.getJSObj());
    }
    
    public LatLng getLatLng() {
        return new LatLng(getLatLngInternal(jsObj));
    }
    
    public void setLatLng(LatLng latlng) {
        setLatLngInternal(jsObj, latlng.getJSObj());
    }
    
    public LatLng getContent() {
        return new LatLng(getContentInternal(jsObj));
    }
    
    public void setContent(String content) {
        setContentInternal(jsObj, content);
    }
    
    public void update() {
        updateInternal(jsObj);
    }
    
    @JavaScriptBody(args = { "jsObj", "map" }, body = 
        "jsObj.addTo(map);")
    private static native void addToInternal(Object jsObj, Object map);
    
    @JavaScriptBody(args = { "jsObj", "map" }, body = 
        "jsObj.openOn(map);")
    private static native void openOnInternal(Object jsObj, Object map);

    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.getLatLng();")
    private static native Object getLatLngInternal(Object jsObj);

    @JavaScriptBody(args = { "jsObj", "latlng" }, body = 
        "jsObj.setLatLng(latlng);")
    private static native void setLatLngInternal(Object jsObj, Object latlng);
    
    @JavaScriptBody(args = { "jsObj" }, body = 
        "return ''+jsObj.getContent();")
    private static native String getContentInternal(Object jsObj);

    @JavaScriptBody(args = { "jsObj", "content" }, body = 
        "jsObj.setContent(content);")
    private static native void setContentInternal(Object jsObj, Object content);
    
    @JavaScriptBody(args = { "jsObj" }, body = 
        "jsObj.update();")
    private static native void updateInternal(Object jsObj);
    


}