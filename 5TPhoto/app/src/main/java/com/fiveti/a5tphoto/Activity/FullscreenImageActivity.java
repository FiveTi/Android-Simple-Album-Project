package com.fiveti.a5tphoto.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.FileProvider;
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
    //Đường dẫn của ảnh hiện tại
    String curPath = "";
    private ViewPager viewPager;
    private FullscreenImageAdapter fullScreenImageAdapter;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm");

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

        //Khởi tạo context
        context = FullscreenImageActivity.this;

        //Khởi tạo toolbar
        toolbar = findViewById(R.id.nav_actionBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar());
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
        curPath = allPath.get(posAlbum).getAllImagePath().get(posImage);
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
                try {
                    ExifInterface exifInterface = new ExifInterface(curPath);
                    String info = "";
                    String attribute = "";
                    attribute = getImageInfo(exifInterface);
                    File file = new File(curPath);

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    double size = file.length();
                    String temp = "";

                    if (size > 1024 * 1024) {
                        temp = decimalFormat.format(size / (1024 * 1024)) + " MB";
                    } else {
                        if (size > 1024) {
                            temp = decimalFormat.format(size / 1024) + " KB";
                        } else {
                            temp = decimalFormat.format(size) + " B";
                        }
                    }

                    info = simpleDateFormat.format(file.lastModified())
                            + "\n\n" + curPath + "\n"
                            + temp + "    "
                            + attribute;

                    //Show dialog
                    TextView title = new TextView(getApplicationContext());
                    title.setPadding(30, 30, 30, 0);
                    title.setTextSize(25);
                    title.setTypeface(null, Typeface.BOLD);
                    title.setText("Image infomation");

                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert).create();
                    dialog.setCustomTitle(title);
                    dialog.setMessage(info);
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    dialog.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.action_slideShow:
                Toast.makeText(this, "Set as", Toast.LENGTH_SHORT).show();
                Intent intentSlideshow = new Intent(context, SlideshowActivity.class);
                intentSlideshow.putExtra("idImage", posImage); // Lấy position id và truyền cho SlideShowActivity
                intentSlideshow.putExtra("idAlbum", posAlbum);
                startActivity(intentSlideshow);
                return true;

            case R.id.action_setAs:

                return true;
        }

        return true;
    }

    private String getImageInfo(ExifInterface exifInterface) {
        String imgAttribute = "";

        //Lấy ra độ phân giải của ảnh
        if (exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0) == 0) {
            return imgAttribute;
        } else {
            imgAttribute += exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH) +
                    "x" + exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
            if (exifInterface.getAttribute(ExifInterface.TAG_MODEL) == null) {
                return imgAttribute;
            }
        }

        //Lấy ra model của điện thoại
        imgAttribute += "\n\n" + exifInterface.getAttribute(ExifInterface.TAG_MODEL) + "\n";

        // Lấy ra khẩu độ của ảnh
        final DecimalFormat apertureFormat = new DecimalFormat("#.#");
        String aperture = exifInterface.getAttribute(ExifInterface.TAG_F_NUMBER);
        if (aperture != null) {
            Float parseFloat = Float.parseFloat(aperture);
            apertureFormat.format(parseFloat);
            imgAttribute += "f/" + parseFloat + "    ";
        } else {
            imgAttribute += "    ";
        }

        //Lấy ra tiêu cự ống kính
        imgAttribute += exifInterface.getAttributeDouble(ExifInterface.TAG_FOCAL_LENGTH, 0) + "mm" + "    ";

        //Lấy ra Iso của ảnh
        imgAttribute += "ISO: " + exifInterface.getAttribute(ExifInterface.TAG_ISO_SPEED_RATINGS) + "    ";

        //Kiểm tra xem có bật flash hay không
        if (exifInterface.getAttributeInt(ExifInterface.TAG_FLASH, 0) == 0) {
            imgAttribute += "flash: off";
        } else {
            imgAttribute += "flash: on";
        }

        return imgAttribute;
    }


    //Vào chế độ ẩn toàn màn hình
    public void EnterFullScreen() {
        getSupportActionBar().hide();
        fullImageNav.setVisibility(View.GONE);
        hideView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    //Thoát chế độ ẩn toàn màn hình
    public void ExitFullScreen() {
        fullImageNav.setVisibility(View.VISIBLE);
        getSupportActionBar().show();
    }

}
