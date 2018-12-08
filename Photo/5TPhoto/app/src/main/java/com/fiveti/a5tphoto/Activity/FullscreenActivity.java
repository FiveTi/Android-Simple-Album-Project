package com.fiveti.a5tphoto.Activity;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fiveti.a5tphoto.ImagePath;
import com.fiveti.a5tphoto.R;

import java.util.ArrayList;
import java.util.Objects;

public class FullscreenActivity extends AppCompatActivity {
    public static ArrayList<ImagePath> allPath = new ArrayList<>();
    private String ARRAY_PATH = "array_path";
    int posImage;
    int position;
    ImageView fullImage;
    Toolbar toolbar;
    static int hide = 0;
    BottomNavigationView fullImageNav;
    View hideView;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage_activity);
        hideView = getWindow().getDecorView();

        fullImage = (ImageView) findViewById(R.id.fullImage);

        //Khởi tạo toolbar
        toolbar = findViewById(R.id.nav_actionBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Ẩn toàn bộ thanh thông báo, thanh điều hướng chỉ hiện lên khi được vuốt lên)
        hideView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Khởi tạo bottom navigation bar
        fullImageNav = findViewById(R.id.nav_bottom);

        Bundle bFullImage = this.getIntent().getExtras();
        allPath = (ArrayList<ImagePath>) bFullImage.getSerializable(ARRAY_PATH);
        position = bFullImage.getInt("posAlbum");
        posImage = bFullImage.getInt("posImage");

        Glide.with(fullImage.getContext()).load("file://" + allPath.get(position).getAllImagePath().get(posImage))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(fullImage);

    }



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

    //Vào chế độ ẩn toàn màn hình
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void EnterFullScreenView() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        fullImageNav.setVisibility(View.GONE);
        hideView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void LeaveFullScreenView() {
        fullImageNav.setVisibility(View.VISIBLE);
        Objects.requireNonNull(getSupportActionBar()).show();
    }
}
