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
import com.oleg.hubal.mediagallery.Utility;
import com.oleg.hubal.mediagallery.adapter.ThumbnailAdapter;
import com.oleg.hubal.mediagallery.listener.OnActiveMediaPathListener;
import com.oleg.hubal.mediagallery.listener.OnActiveThumbnailListener;
import com.oleg.hubal.mediagallery.model.MediaUnit;

import java.util.ArrayList;

/**
 * Created by User on 11.11.2016.
 */

public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
        OnActiveThumbnailListener {
    private String mCursorColumnID;
    private String mCursorColumnPath;
    private String mCursorColumnDate;
    private String mCursorColumnMIME;
    private Uri mMediaUri;

    private ArrayList<MediaUnit> mMediaDataList;
    private ThumbnailAdapter mAdapter;

    private OnActiveMediaPathListener mActiveMediaPathListener;
    private int mPagerPosition;

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
        mActiveMediaPathListener = (OnActiveMediaPathListener) getActivity();

        mCursorColumnID = MediaStore.MediaColumns._ID;
        mCursorColumnMIME = MediaStore.MediaColumns.MIME_TYPE;

        if (mPagerPosition == Constants.PAGE_IMAGE) {
            mMediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            mCursorColumnPath =  MediaStore.Images.Media.DATA;
            mCursorColumnDate = MediaStore.Images.ImageColumns.DATE_TAKEN;
        } else if (mPagerPosition == Constants.PAGE_VIDEO) {
            mMediaUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            mCursorColumnPath =  MediaStore.Video.Media.DATA;
            mCursorColumnDate = MediaStore.Video.VideoColumns.DATE_TAKEN;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery , container, false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rv_gallery_recycler);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), Constants.IMAGE_COUNT);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ThumbnailAdapter(null, this);
        recyclerView.setAdapter(mAdapter);

        getLoaderManager().initLoader(mPagerPosition, null, this);

        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), mMediaUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mMediaDataList = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                int id = data.getInt(data.getColumnIndex(mCursorColumnID));
                String path = data.getString(data.getColumnIndex(mCursorColumnPath));
                String date = data.getString(data.getColumnIndex(mCursorColumnDate));
                String mimeType = data.getString(data.getColumnIndex(mCursorColumnMIME));
                mMediaDataList.add(new MediaUnit(id, path, date, mimeType));
            } while (data.moveToNext());
        }
        Utility.sortByDate(mMediaDataList);
        mAdapter.swapData(mMediaDataList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onActiveThumbnail(int positions) {
        String path = mMediaDataList.get(positions).getPath();
        if (mPagerPosition == Constants.PAGE_IMAGE) {
            mActiveMediaPathListener.onActiveImagePath(path);
        }
        if (mPagerPosition == Constants.PAGE_VIDEO) {
            mActiveMediaPathListener.onActiveVideoPath(path);
        }
    }


}
