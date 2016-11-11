package com.oleg.hubal.mediagallery.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.oleg.hubal.mediagallery.R;
import com.oleg.hubal.mediagallery.model.MediaManager;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 11.11.2016.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> {

    private MediaManager mMediaManager;
    private Context mContext;
    private int mSize;

    public ThumbnailAdapter(Context context, MediaManager mediaManager) {
        init(context, mediaManager);
    }

    private void init(Context context, MediaManager mediaManager) {
        mContext = context;
        if (mediaManager != null) {
            Log.d(TAG, "init: ");
            mMediaManager = mediaManager;
            mSize = mediaManager.getSize();
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
        Log.d(TAG, "onBindViewHolder: " + mSize);
        int id = mMediaManager.getMediaIdByPosition(position + 1);
        Bitmap bitmap = MediaStore.Images.Thumbnails
                .getThumbnail(mContext.getContentResolver(), id, MediaStore.Images.Thumbnails.MINI_KIND, null);
        holder.ivThumbnail.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return mSize;
    }

    public void swapData(Context context, MediaManager mediaManager) {
        init(context, mediaManager);
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
