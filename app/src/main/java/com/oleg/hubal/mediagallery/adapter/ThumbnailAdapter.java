package com.oleg.hubal.mediagallery.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.oleg.hubal.mediagallery.R;
import com.oleg.hubal.mediagallery.model.MediaUnit;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by User on 11.11.2016.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> {

    private static final String TAG = "123123";
    private ArrayList<MediaUnit> mMediaDataList;
    private Context mContext;
    ImageLoader mImageLoader;


    public ThumbnailAdapter(Context context, ArrayList<MediaUnit> mediaDataList) {
        mMediaDataList = mediaDataList;
        mContext = context;
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
        String path = mMediaDataList.get(position).getPath();
        Uri uri = Uri.fromFile(new File(path));
        mImageLoader.displayImage(uri.toString(), holder.ivThumbnail);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
        }
    }
}
