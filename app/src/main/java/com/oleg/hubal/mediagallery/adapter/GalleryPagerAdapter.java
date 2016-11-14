package com.oleg.hubal.mediagallery.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oleg.hubal.mediagallery.Constants;
import com.oleg.hubal.mediagallery.fragment.GalleryFragment;

/**
 * Created by User on 11.11.2016.
 */

public class GalleryPagerAdapter extends FragmentPagerAdapter {
    public GalleryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GalleryFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return Constants.PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.PAGE_LIST[position];
    }
}