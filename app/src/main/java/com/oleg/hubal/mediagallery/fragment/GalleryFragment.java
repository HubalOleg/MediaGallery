package com.oleg.hubal.mediagallery.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oleg.hubal.mediagallery.Constants;
import com.oleg.hubal.mediagallery.R;
import com.oleg.hubal.mediagallery.adapter.ThumbnailAdapter;
import com.oleg.hubal.mediagallery.model.MediaManager;

/**
 * Created by User on 11.11.2016.
 */

public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private MediaManager mMediaManager;
    private String mCursorColumnID = MediaStore.MediaColumns._ID;
    private String mCursorColumnMedia;
    private String[] mProjection;
    private Uri mMediaUri;

    private int mPagerPosition;

    private ThumbnailAdapter mAdapter;

    public static GalleryFragment newInstance(int position) {
        GalleryFragment galleryFragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.PAGER_POSITION, position);
        galleryFragment.setArguments(args);
        return galleryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPagerPosition = getArguments().getInt(Constants.PAGER_POSITION);
        mMediaManager = new MediaManager();

        if (mPagerPosition == Constants.PAGE_PHOTO) {
            mCursorColumnMedia =  MediaStore.Images.Media.DATA;
            mMediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (mPagerPosition == Constants.PAGE_VIDEO) {
            mCursorColumnMedia =  MediaStore.Video.Media.DATA;
            mMediaUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        mProjection = new String[] {mCursorColumnID, mCursorColumnMedia};
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery , container, false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rv_gallery_recycler);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ThumbnailAdapter(mMediaManager);
        recyclerView.setAdapter(mAdapter);

        getLoaderManager().initLoader(mPagerPosition, null, this);

        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), mMediaUri, mProjection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {
            do {
                int id = data.getInt(data.getColumnIndex(mCursorColumnID));
                String path = data.getString(data.getColumnIndex(mCursorColumnMedia));
                mMediaManager.addMediaIdPath(id, path);
            } while (data.moveToNext());
        }
        mAdapter.swapData(mMediaManager);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
