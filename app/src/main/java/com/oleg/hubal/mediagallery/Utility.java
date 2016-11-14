package com.oleg.hubal.mediagallery;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.oleg.hubal.mediagallery.model.MediaUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by User on 12.11.2016.
 */

public class Utility {
    public static void configureDefaultImageLoader(Context context) {
        DisplayImageOptions mOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoaderConfiguration defaultConfiguration
                = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(mOptions)
                .threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheExtraOptions(480, 480, null)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(defaultConfiguration);
    }

    public static void sortByDate(ArrayList<MediaUnit> mediaDataList) {
        Collections.sort(mediaDataList, new Comparator<MediaUnit>() {
            @Override
            public int compare(MediaUnit unit1, MediaUnit unit2) {
                return Long.valueOf(unit2.getDate()).compareTo(Long.valueOf((unit1.getDate())));
            }
        });
    }

    public static boolean checkIsPreviousThumbnailVisible(int activePosition, int previousPosition) {
        if (Math.abs(activePosition - previousPosition) <= Constants.ROW_COUNT * Constants.IMAGE_COUNT) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<MediaUnit> sortMimeType(ArrayList<MediaUnit> mediaDataList, String mimeType) {
        ArrayList<MediaUnit> sortedMediaList = new ArrayList<MediaUnit>();
        for (MediaUnit mediaUnit : mediaDataList) {
            if (mediaUnit.getMimeType() == mimeType) {
                sortedMediaList.add(mediaUnit);
            }
        }
        return sortedMediaList;
    }
}
