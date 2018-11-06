package com.duythanhpham.gallery_firstversion.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.duythanhpham.gallery_firstversion.Adapters.ThumnailImageAdapter;
import com.duythanhpham.gallery_firstversion.Misc.ScreenDisplayUtility;
import com.duythanhpham.gallery_firstversion.R;

import java.util.ArrayList;

public class ThumnailImageActivity extends AppCompatActivity {//implements  ThumnailImageAdapter.ImageThumbnailLoader{
//
//    public static final String KEY_IMAGES = "KEY_IMAGES";
//    public static final String KEY_TITLE = "KEY_TITLE";
//
//    private RecyclerView recyclerView;
//
//    private ArrayList<String> images;
//    private String title;
//    private static ThumnailImageAdapter.ImageThumbnailLoader imageThumbnailLoader;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            Bundle extras = intent.getExtras();
//            if (extras != null) {
//                images = extras.getStringArrayList(KEY_IMAGES);
//                title = extras.getString(KEY_TITLE);
//            }
//        }
//
//        setContentView(R.layout.image_thumnail);
//
//        bindViews();
//        setUpRecyclerView();
//    }
//
//    @Override
//    public void loadImageThumbnail(ImageView iv, String imageUrl, int dimension) {
//        imageThumbnailLoader.loadImageThumbnail(iv, imageUrl, dimension);
//    }
//
//    private void bindViews() {
//        recyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
//
//    }
//
//    private void setUpRecyclerView() {
//        int numOfColumns = 4;
////        if (ScreenDisplayUtility.isInLandscapeMode(this)) {
////            numOfColumns = 4;
////        } else {
////            numOfColumns = 3;
////        }
//
//        recyclerView.setLayoutManager(new GridLayoutManager(ThumnailImageActivity.this, numOfColumns));
//        ThumnailImageAdapter thumnailImageAdapter = new ThumnailImageAdapter(this, images);
//        thumnailImageAdapter.setImageThumbnailLoader(this);
//
//        recyclerView.setAdapter(thumnailImageAdapter);
//    }
}
