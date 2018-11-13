package com.duythanhpham.gallery_second_version.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duythanhpham.gallery_second_version.Adapter.FullScreenImageAdapter;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IFullScreenImageLoader;
import com.duythanhpham.gallery_second_version.R;

import java.util.ArrayList;
import java.util.List;

public class FullScreenImageActivity extends AppCompatActivity implements IFullScreenImageLoader {
    // region Constants
    public static final String KEY_IMAGE_LIST = "KEY_IMAGE_LIST";
    public static final String KEY_POSITION = "KEY_POSITION";
    public static final Integer numOfColumns = 3;
    // endregion

    private ViewPager viewPager;
    // region Member Variables
    private List<GalleryImage> imageList;
    private int position;
    private int function;
    private static IFullScreenImageLoader iFullScreenImageLoader;
    // endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_image_activity);

        getSupportActionBar().setTitle("FullScreenImage");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.vp);

        Intent intent = getIntent();
        if (intent != null) {
            imageList = (ArrayList<GalleryImage>) intent.getSerializableExtra(KEY_IMAGE_LIST);

            Bundle bundle = intent.getExtras();
            position = bundle.getInt(KEY_POSITION);
        }

        SetUpViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fullscreen_image, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_Info:

                break;
            case R.id.menu_Delete:

                break;
            case R.id.menu_Edit:

                break;
            case R.id.menu_Rotate:

              //xử lý xoay ảnh
                break;
            case R.id.menu_CropImage:

                //xử lý crop ảnh
            case R.id.menu_SetWallpaper:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void LoadFullScreenImage(ImageView iv, Integer imageID, int width, LinearLayout bglinearLayout) {
        iFullScreenImageLoader.LoadFullScreenImage(iv, imageID, width, bglinearLayout);
    }

    public void SetUpViewPager() {
        ArrayList<GalleryImage> images = new ArrayList<>();
        images.addAll(imageList);

        FullScreenImageAdapter fullScreenImageAdapter = new FullScreenImageAdapter(this, images);
        fullScreenImageAdapter.setFuntion(function);
        fullScreenImageAdapter.setFullScreenImageLoader(this);
        viewPager.setAdapter(fullScreenImageAdapter);
        viewPager.addOnPageChangeListener(viewPagerOnPageChangeListener);
        viewPager.setCurrentItem(position);
    }


    // region Listeners
    private final ViewPager.OnPageChangeListener viewPagerOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (viewPager != null) {
                viewPager.setCurrentItem(position);

                //setActionBarTitle(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    // endregion

    private void removeListeners() {
        viewPager.removeOnPageChangeListener(viewPagerOnPageChangeListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeListeners();
    }

    public static void setFullScreenImageLoader(IFullScreenImageLoader loader) {
        iFullScreenImageLoader = loader;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
