package com.oleg.hubal.mediagallery.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.oleg.hubal.mediagallery.R;
import com.oleg.hubal.mediagallery.Utility;
import com.oleg.hubal.mediagallery.listener.OnActiveThumbnailListener;
import com.oleg.hubal.mediagallery.model.MediaUnit;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by User on 11.11.2016.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> implements View.OnClickListener {

    private ImageLoader mImageLoader;

    private OnActiveThumbnailListener mThumbnailListener;
    private ArrayList<MediaUnit> mMediaDataList;

    private RoundedImageView mActiveView;
    private int mActivePosition = 0;


    public ThumbnailAdapter(ArrayList<MediaUnit> mediaDataList, OnActiveThumbnailListener thumbnailListener) {
        mMediaDataList = mediaDataList;
        mThumbnailListener = thumbnailListener;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_thumbnail, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoundedImageView rivThumbnail = holder.rivThumbnail;
        rivThumbnail.setImageResource(android.R.drawable.screen_background_light);
        rivThumbnail.setBorderWidth(0f);
        rivThumbnail.setOnClickListener(this);
        rivThumbnail.setTag(position);

        if (position == mActivePosition) {
            setActiveView(position, rivThumbnail);
            setImageViewWithCorner(position, rivThumbnail);
        } else {
            setImageViewWithoutCorner(position, rivThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        if (mMediaDataList == null) {
            return 0;
        }
        return mMediaDataList.size();
    }

    public void swapData(ArrayList<MediaUnit> mediaDataList) {
        mMediaDataList = mediaDataList;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();

        if (Utility.checkIsPreviousThumbnailVisible(position, mActivePosition)) {
            setImageViewWithoutCorner(mActivePosition, mActiveView);
        }

        setActiveView(position, (RoundedImageView) view);
    }

    private void setActiveView(int position, RoundedImageView rivThumbnail) {
        mActivePosition = position;
        mActiveView = rivThumbnail;
        setImageViewWithCorner(position, rivThumbnail);
        mThumbnailListener.onActiveThumbnail(position);
    }

    private void setImageViewWithCorner(int position, RoundedImageView rivThumbnail) {
        rivThumbnail.setBorderWidth(R.dimen.card_border_width);
        String path = mMediaDataList.get(position).getPath();
        Uri uri = Uri.fromFile(new File(path));
        mImageLoader.displayImage(uri.toString(), rivThumbnail);
    }

    private void setImageViewWithoutCorner(int position, RoundedImageView rivThumbnail) {
        rivThumbnail.setBorderWidth(0f);
        String path = mMediaDataList.get(position).getPath();
        Uri uri = Uri.fromFile(new File(path));
        mImageLoader.displayImage(uri.toString(), rivThumbnail);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView rivThumbnail;

        ViewHolder(View itemView) {
            super(itemView);

            rivThumbnail = (RoundedImageView) itemView.findViewById(R.id.riv_thumbnail_card);
        }
    }
}
