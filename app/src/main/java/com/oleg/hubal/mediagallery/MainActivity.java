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
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.oleg.hubal.mediagallery.adapter.GalleryPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static String[] PERMISSION_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_STORAGE = 0;

    private GalleryPagerAdapter mGalleryPagerAdapter;

    private LinearLayout mLayout;
    private FrameLayout mDisplayContainer;
    private ViewPager mGalleryPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (LinearLayout) findViewById(R.id.activity_main);
        mDisplayContainer = (FrameLayout) findViewById(R.id.fl_display_container);

        showGalleryWithPermission();
    }

    private void showGalleryWithPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestExternalStoragePermission();
        } else {
            startGalleryPager();
            Utility.configureDefaultImageLoader(getApplicationContext());
        }
    }

    private void startGalleryPager() {
        mGalleryPager = (ViewPager) findViewById(R.id.vp_gallery_pager);
        mGalleryPagerAdapter = new GalleryPagerAdapter(getSupportFragmentManager());
        mGalleryPager.setAdapter(mGalleryPagerAdapter);
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
                                    .requestPermissions(MainActivity.this, PERMISSION_STORAGE,
                                            REQUEST_STORAGE);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, REQUEST_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE) {
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
}
