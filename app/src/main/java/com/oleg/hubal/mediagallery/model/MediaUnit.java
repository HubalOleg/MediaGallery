package com.oleg.hubal.mediagallery.model;

/**
 * Created by User on 12.11.2016.
 */

public class MediaUnit {

    private int id;
    private String path;
    private String date;
    private String mimeType;

    public MediaUnit(int id, String path, String date, String mimeType) {
        this.id = id;
        this.path = path;
        this.date = date;
        this.mimeType = mimeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
