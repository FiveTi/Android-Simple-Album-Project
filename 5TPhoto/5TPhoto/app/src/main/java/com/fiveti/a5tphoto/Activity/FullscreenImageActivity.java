package com.fiveti.a5tphoto.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.media.ExifInterface;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.fiveti.a5tphoto.Adapter.FullscreenImageAdapter;
import com.fiveti.a5tphoto.Album;
import com.fiveti.a5tphoto.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class FullscreenImageActivity extends AppCompatActivity {
    public static ArrayList<Album> allPath = new ArrayList<>();
    private String ARRAY_PATH = "array_path";
    Context context;
    int posImage;
    int posAlbum;
    PhotoView fullImage;
    Toolbar toolbar;
    // public static int hide = 0;
    BottomNavigationView fullImageNav;
    View hideView;
    String curPath = "";
    private ViewPager viewPager;
    private FullscreenImageAdapter fullScreenImageAdapter;


    SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen_image_viewpager);

        hideView = getWindow().getDecorView();

        viewPager = findViewById(R.id.viewpager);
        fullImageNav = findViewById(R.id.nav_bottom);

        context = FullscreenImageActivity.this;
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

        //Khởi tạo bottom navigation bar
        fullImageNav = findViewById(R.id.nav_bottom);

        Bundle bFullImage = this.getIntent().getExtras();
        //allPath = new ArrayList<>();
        allPath = (ArrayList<Album>) bFullImage.getSerializable(ARRAY_PATH);
        posAlbum = bFullImage.getInt("posAlbum");
        posImage = bFullImage.getInt("posImage");
        curPath = "file://" + allPath.get(posAlbum).getAllImagePath().get(posImage);
        setupViewPager();
    }

    private void setupViewPager() {
        // ArrayList<Album> images = new ArrayList<>();
        // images.addAll(allPath);

        fullScreenImageAdapter = new FullscreenImageAdapter(this, allPath, posAlbum);

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


                // -----  Tạo dialog để xuất ra detail -----

                TextView title = new TextView(getApplicationContext());
                title.setPadding(46, 40, 0, 0);
                title.setText("Image infomation");
                title.setTextSize(23.0f);
                title.setTypeface(null, Typeface.BOLD);
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert).create();


                dialog.setCustomTitle(title);
                dialog.setMessage("TTTTT");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.show();

                return true;

            case R.id.action_slideShow:
                Toast.makeText(this, "Set as", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_setAs:
                Toast.makeText(this, "Set as", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    private String showInfo(ExifInterface exif) {
        String myAttribute = "";

        if (exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0) == 0) {
            return myAttribute;
        } else {
            myAttribute += "\n\nResolution: " + exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH) +
                    "x" + exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);

            if (exif.getAttribute(ExifInterface.TAG_MODEL) == null) {
                return myAttribute;
            }
        }

        // Lấy aperture
        final DecimalFormat apertureFormat = new DecimalFormat("#.#"); // Tạo format cho aperture
        String aperture = exif.getAttribute(ExifInterface.TAG_F_NUMBER);
        if (aperture != null) {
            Double aperture_double = Double.parseDouble(aperture);
            apertureFormat.format(aperture_double);
            myAttribute += "\n\nAperture: f/" + aperture_double + "\n\n";
        } else {
            myAttribute += "\n\nAperture: unknown\n\n";
        }

        // Lấy exposure time
        String ExposureTime = exif.getAttribute(ExifInterface.TAG_EXPOSURE_TIME);
        Double ExposureTime_double = Double.parseDouble(ExposureTime);
        Double Denominator = 1 / ExposureTime_double;

        ExposureTime = 1 + "/" + String.format("%.0f", Denominator);

        myAttribute += "Exposure Time: " + ExposureTime + "s\n\n";

        if (exif.getAttributeInt(ExifInterface.TAG_FLASH, 0) == 0) {
            myAttribute += "Flash: Off\n\n";
        } else {
            myAttribute += "Flash: On\n\n";
        }
        myAttribute += "Focal Length: " + exif.getAttributeDouble(ExifInterface.TAG_FOCAL_LENGTH, 0) + "mm\n\n";
        myAttribute += "ISO Value: " + exif.getAttribute(ExifInterface.TAG_ISO_SPEED_RATINGS) + "\n\n";
        myAttribute += "Model: " + exif.getAttribute(ExifInterface.TAG_MODEL);

        return myAttribute;
    }

    //Vào chế độ ẩn toàn màn hình
    public void EnterFullScreenView() {
        getSupportActionBar().hide();
        fullImageNav.setVisibility(View.GONE);
        hideView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    //Thoát chế độ ẩn toàn màn hình
    public void LeaveFullScreenView() {
        fullImageNav.setVisibility(View.VISIBLE);
        getSupportActionBar().show();
    }

}
