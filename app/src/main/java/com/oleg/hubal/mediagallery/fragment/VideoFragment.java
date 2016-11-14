package com.oleg.hubal.mediagallery.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crust87.texturevideoview.widget.TextureVideoView;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.oleg.hubal.mediagallery.R;
import com.oleg.hubal.mediagallery.listener.OnSetMediaListener;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 13.11.2016.
 */

public class VideoFragment extends Fragment implements OnSetMediaListener, View.OnClickListener,
        OnRangeSeekbarFinalValueListener{

    private boolean mIsVideoPlaying = false;

    private MediaPlayer mMediaPlayer;

    private CrystalRangeSeekbar mVideoSeekBar;
    private TextureVideoView mVideoTexture;

    private int mProgressMinValue, mProgressMaxValue;

    private String mPath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        mVideoTexture = (TextureVideoView) v.findViewById(R.id.trv_video_surface);

        mVideoSeekBar = (CrystalRangeSeekbar) v.findViewById(R.id.rsv_video_bar);

        mVideoSeekBar.setOnRangeSeekbarFinalValueListener(this);

        return v;
    }

    @Override
    public void onSetMedia(String path) {
        mPath = path;
        if (mVideoTexture != null) {
            mProgressMinValue = 0;
            mProgressMaxValue = 100;
            mIsVideoPlaying = true;
            mVideoTexture.setVideoPath(path);
            mVideoTexture.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPath != null) {
           onSetMedia(mPath);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        if (mIsVideoPlaying) {
            mIsVideoPlaying = !mIsVideoPlaying;
        } else {
            mIsVideoPlaying = !mIsVideoPlaying;
        }
    }

    @Override
    public void finalValue(Number minValue, Number maxValue) {
        mProgressMinValue = Integer.parseInt(String.valueOf(minValue));
        mProgressMaxValue = Integer.parseInt(String.valueOf(maxValue));
        Log.d(TAG, "valueChanged: " + mProgressMaxValue + "  " + mProgressMinValue);
    }
}
