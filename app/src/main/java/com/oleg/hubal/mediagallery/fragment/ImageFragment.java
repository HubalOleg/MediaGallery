package com.oleg.hubal.mediagallery.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.oleg.hubal.mediagallery.R;
import com.oleg.hubal.mediagallery.listener.OnSetMediaListener;

import java.io.File;

/**
 * Created by User on 13.11.2016.
 */

public class ImageFragment extends Fragment implements OnSetMediaListener {
    private ImageView mImageView;
    private String mPath;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        mImageView = (ImageView) v.findViewById(R.id.iv_big_size_image);
        return v;
    }

    @Override
    public void onSetMedia(String path) {
        mPath = path;
        File file = new File(path);
        Uri imageUri = Uri.fromFile(file);
        ImageLoader.getInstance().displayImage(imageUri.toString(), mImageView);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPath != null) {
            onSetMedia(mPath);
        }
    }
}
