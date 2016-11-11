package com.oleg.hubal.mediagallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.oleg.hubal.mediagallery.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 11.11.2016.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> {

    private ArrayList<String> mMediaPathList;
    private Context mContext;
    private int mSize;

    public ThumbnailAdapter(Context context, ArrayList<String> mediaPathList) {
        init(context, mediaPathList);
    }

    private void init(Context context, ArrayList<String> mediaPathList) {
        mContext = context;
        if (mediaPathList != null) {
            Log.d(TAG, "init: ");
            mMediaPathList = mediaPathList;
            mSize = mediaPathList.size();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_thumbnail, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String path = mMediaPathList.get(position);
//        Bitmap bitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path), 500, 500);
//
        holder.ivThumbnail.setImageDrawable(mContext.getDrawable(android.R.drawable.btn_minus));
    }

    @Override
    public int getItemCount() {
        return mSize;
    }

    public void swapData(Context context, ArrayList<String> mediaPathList) {
        init(context, mediaPathList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
        }
    }
}
