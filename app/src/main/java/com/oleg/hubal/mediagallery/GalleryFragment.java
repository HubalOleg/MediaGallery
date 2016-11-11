package com.oleg.hubal.mediagallery;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 11.11.2016.
 */

public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private Uri mImageUri;

    private String[] mProjection = {
            MediaStore.Images.Media.DATA
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery , container, false);

        mImageUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        getLoaderManager().initLoader(0, null, this);

        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(getContext(),
                mImageUri,
                mProjection,
                null,
                null,
                null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished: " + data.getCount());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
