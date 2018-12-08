package com.fiveti.a5tphoto.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fiveti.a5tphoto.Adapter.FullscreenImageAdapter;
import com.fiveti.a5tphoto.Album;
import com.fiveti.a5tphoto.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.Objects;

public class FullscreenImageActivity extends AppCompatActivity {
    public static ArrayList<Album> allPath = new ArrayList<>();
    private String ARRAY_PATH = "array_path";
    int posImage;
    int posAlbum;
    PhotoView fullImage;
    Toolbar toolbar;
    static int hide = 0;
    BottomNavigationView fullImageNav;
    View hideView;

    private ViewPager viewPager;
    FullscreenImageAdapter fullScreenImageAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image_viewpager);

        hideView = getWindow().getDecorView();

        viewPager = findViewById(R.id.viewpager);

        //Khởi tạo toolbar
        toolbar = findViewById(R.id.nav_actionBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("FullScreenImageActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });


        //Ẩn toàn bộ thanh thông báo, thanh điều hướng chỉ hiện lên khi được vuốt lên)
        hideView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Khởi tạo bottom navigation bar
        fullImageNav = findViewById(R.id.nav_bottom);

        Bundle bFullImage = this.getIntent().getExtras();
        //allPath = new ArrayList<>();
        allPath = (ArrayList<Album>) bFullImage.getSerializable(ARRAY_PATH);
        posAlbum = bFullImage.getInt("posAlbum");
        posImage = bFullImage.getInt("posImage");

        setupViewPager();
    }

    private void setupViewPager() {
       // ArrayList<Album> images = new ArrayList<>();
       // images.addAll(allPath);

        fullScreenImageAdapter = new FullscreenImageAdapter(this, allPath, posAlbum, posImage);

        viewPager.setAdapter(fullScreenImageAdapter);
        viewPager.addOnPageChangeListener(viewPagerOnPageChangeListener);
        viewPager.setCurrentItem(posImage);
        fullScreenImageAdapter.notifyDataSetChanged();
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

                //setActionBarTitle(posAlbum);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    // endregion

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_full_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        hideView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        switch (id) {
            case R.id.action_info:
                Toast.makeText(this, "Show info", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_slideShow:

                break;

            case R.id.action_setAs:
                Toast.makeText(this, "Set as", Toast.LENGTH_SHORT).show();

                break;
        }


        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        Toast.makeText(this,"HIHI", Toast.LENGTH_SHORT).show();
//        onBackPressed();
//        return true;
//    }
}
