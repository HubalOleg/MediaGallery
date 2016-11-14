package com.oleg.hubal.mediagallery.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crust87.texturevideoview.widget.TextureVideoView;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.oleg.hubal.mediagallery.R;
import com.oleg.hubal.mediagallery.listener.OnSetMediaListener;

/**
 * Created by User on 13.11.2016.
 */

public class VideoFragment extends Fragment implements OnSetMediaListener, View.OnClickListener,
        OnRangeSeekbarFinalValueListener, MediaPlayer.OnPreparedListener {

    final Handler loopHandler = new Handler();

    private CrystalRangeSeekbar mVideoSeekBar;
    private TextureVideoView mVideoTexture;

    private int mProgressMinValue;
    private int mProgressMaxValue;

    private boolean mIsVideoPlaying = false;

    private String mPath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        mVideoTexture = (TextureVideoView) v.findViewById(R.id.trv_video_surface);
        mVideoTexture.setOnClickListener(this);
        mVideoTexture.setOnPreparedListener(this);

        mVideoSeekBar = (CrystalRangeSeekbar) v.findViewById(R.id.rsv_video_bar);
        mVideoSeekBar.setOnRangeSeekbarFinalValueListener(this);

        return v;
    }

    @Override
    public void onSetMedia(String path) {
        mPath = path;

        if (mVideoTexture != null) {
            mVideoTexture.setVideoPath(path);
            mVideoTexture.start();
            mIsVideoPlaying = true;
            mVideoSeekBar.apply();
            startPositionCounter();
        }
    }

    private void startPositionCounter() {
        loopHandler.removeCallbacksAndMessages(null);
        loopHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mVideoTexture.getCurrentPosition() > mProgressMaxValue) {
                    mVideoTexture.seekTo(mProgressMinValue);
                }
                loopHandler.postDelayed(this, 200);
            }
        }, 200);
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
        mVideoTexture.stopPlayback();
        loopHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClick(View view) {
        if (mIsVideoPlaying) {
            mVideoTexture.pause();
            mIsVideoPlaying = !mIsVideoPlaying;
        } else {
            mVideoTexture.start();
            mIsVideoPlaying = !mIsVideoPlaying;
        }
    }

    @Override
    public void finalValue(Number minValue, Number maxValue) {
        mProgressMinValue = Integer.parseInt(String.valueOf(minValue));
        mProgressMaxValue = Integer.parseInt(String.valueOf(maxValue));
        mVideoTexture.seekTo(mProgressMinValue);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mProgressMinValue = 0;
        mProgressMaxValue = mediaPlayer.getDuration();
        mVideoSeekBar.setMinStartValue(mProgressMinValue);
        mVideoSeekBar.setMaxStartValue(mProgressMaxValue);
        mVideoSeekBar.setMinValue(mProgressMinValue);
        mVideoSeekBar.setMaxValue(mProgressMaxValue);
    }
}
