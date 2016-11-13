package com.oleg.hubal.mediagallery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oleg.hubal.mediagallery.R;
import com.oleg.hubal.mediagallery.listener.OnSetMediaListener;

/**
 * Created by User on 13.11.2016.
 */

public class VideoFragment extends Fragment implements OnSetMediaListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        return v;
    }

    @Override
    public void onSetMedia(String path) {

    }
}
