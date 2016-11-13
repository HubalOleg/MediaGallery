package com.oleg.hubal.mediagallery;

import android.Manifest;

/**
 * Created by User on 11.11.2016.
 */

public class Constants {

    public static String[] PERMISSION_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final int REQUEST_STORAGE = 0;

    public static final String PAGER_POSITION = "position";

    public static final int PAGE_IMAGE = 0;
    public static final int PAGE_VIDEO = 1;
    public static final int PAGE_COUNT = 2;
    public static final String[] PAGE_LIST = new String[] {
            "Image",
            "Video"
    };

    public static final int IMAGE_COUNT = 5;

}
