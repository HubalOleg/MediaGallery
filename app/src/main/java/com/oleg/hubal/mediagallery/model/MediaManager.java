package com.oleg.hubal.mediagallery.model;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by User on 11.11.2016.
 */

public class MediaManager {

    private TreeMap<Integer, String> mMediaIdPathStorage;

    public MediaManager(TreeMap<Integer, String> mMediaStorage) {
        mMediaIdPathStorage = mMediaStorage;
    }

    public void addMediaIdPath(int id, String path) {
        mMediaIdPathStorage.put(id, path);
    }

    public int getMediaIdByPosition(int position) {
        return mMediaIdPathStorage.get();
    }

    public String getPathById(int id) {
        return mMediaIdPathStorage.get(id);
    }

    public int getSize() {
        return mMediaIdPathStorage.size();
    }
}
