package com.oleg.hubal.mediagallery.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.oleg.hubal.mediagallery.model.MediaManager;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 11.11.2016.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> {

    private MediaManager mMediaManager;

    public ThumbnailAdapter(MediaManager mediaManager) {
        init(mediaManager);
    }

    private void init(MediaManager mediaManager) {
        Log.d(TAG, "init: " + mediaManager.getSize());
        mMediaManager = mediaManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: " + mMediaManager.getSize());
        return 0;
    }

    public void swapData(MediaManager mediaManager) {
        init(mediaManager);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
