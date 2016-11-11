package com.oleg.hubal.mediagallery.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by User on 11.11.2016.
 */

public class MediaManager {

    private Map<Integer, String> mediaIdPathStorage;

    public MediaManager() {
        mediaIdPathStorage = new TreeMap<>();
    }

    public void addMediaIdPath(int id, String path) {
        mediaIdPathStorage.put(id, path);
    }

    public ArrayList<Integer> getMediaIdList() {
        Set<Integer> keySet = mediaIdPathStorage.keySet();
        return new ArrayList<>(keySet);
    }

    public String getPath(int id) {
        return mediaIdPathStorage.get(id);
    }

    public int getSize() {
        return mediaIdPathStorage.size();
    }
}
