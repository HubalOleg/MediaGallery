package com.oleg.hubal.mediagallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.oleg.hubal.mediagallery.adapter.GalleryPagerAdapter;
import com.oleg.hubal.mediagallery.fragment.ImageFragment;
import com.oleg.hubal.mediagallery.fragment.VideoFragment;
import com.oleg.hubal.mediagallery.listener.OnActiveMediaPathListener;
import com.oleg.hubal.mediagallery.listener.OnSetMediaListener;


public class MainActivity extends AppCompatActivity implements OnActiveMediaPathListener,
        ViewPager.OnPageChangeListener {
    private LinearLayout mLayout;

    private GalleryPagerAdapter mGalleryPagerAdapter;
    private ViewPager mGalleryPager;

    private ImageFragment mImageFragment;
    private VideoFragment mVideoFragment;
    private OnSetMediaListener mMediaListener;

    private String mActiveImagePath;
    private String mActiveVideoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utility.configureDefaultImageLoader(getApplicationContext());

        mImageFragment = new ImageFragment();
        mVideoFragment = new VideoFragment();

        showGalleryWithPermission();
        showImageDisplay();
    }

    private void showGalleryWithPermission() {
        mLayout = (LinearLayout) findViewById(R.id.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestExternalStoragePermission();
        } else {
            startGalleryPager();
        }
    }

    private void startGalleryPager() {
        mGalleryPager = (ViewPager) findViewById(R.id.vp_gallery_pager);
        mGalleryPagerAdapter = new GalleryPagerAdapter(getSupportFragmentManager());
        mGalleryPager.addOnPageChangeListener(this);
        mGalleryPager.setAdapter(mGalleryPagerAdapter);
    }

    private void showImageDisplay() {
        mMediaListener = mImageFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_display_container, mImageFragment)
                .commit();
    }

    private void showVideoDisplay() {
        mMediaListener = mVideoFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_display_container, mVideoFragment)
                .commit();
    }

    @Override
    public void onActiveImagePath(String path) {
        mActiveImagePath = path;
        mMediaListener.onSetMedia(mActiveImagePath);
    }

    @Override
    public void onActiveVideoPath(String path) {
        mActiveVideoPath = path;
        mMediaListener.onSetMedia(mActiveVideoPath);
    }

    @Override
    public void onPageSelected(int position) {
        if (position == Constants.PAGE_IMAGE) {
            showImageDisplay();
        }
        if (position == Constants.PAGE_VIDEO) {
            showVideoDisplay();
        }
    }

    private void requestExternalStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Snackbar.make(mLayout, R.string.permission_storage_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(MainActivity.this, Constants.PERMISSION_STORAGE,
                                            Constants.REQUEST_STORAGE);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, Constants.PERMISSION_STORAGE, Constants.REQUEST_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.REQUEST_STORAGE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(mLayout, R.string.permision_available_storage,
                        Snackbar.LENGTH_SHORT).show();
                startGalleryPager();
            } else {
                Snackbar.make(mLayout, R.string.permissions_not_granted,
                        Snackbar.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
