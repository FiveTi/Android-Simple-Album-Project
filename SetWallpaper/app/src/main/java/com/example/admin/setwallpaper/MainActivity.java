package com.example.admin.setwallpaper;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.DiscretePathEffect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnSetWallpaper;
    ImageView imgWallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSetWallpaper = (Button) findViewById(R.id.btnSetWallPaper);
        imgWallpaper = (ImageView) findViewById(R.id.imgWallpaper);

        btnSetWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setWallpaper();
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.setMessage("Please choose option:");

                builder.setPositiveButton("Set Wallpaper", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setWallpaper();

                    }
                });
                builder.setNegativeButton("Set Screen Lock", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    private void setWallpaper() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.thai);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        try {
            wallpaperManager.setBitmap(bitmap);
            Toast.makeText(this, "Set wallpaper successfully!", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error setting wallpaper!", Toast.LENGTH_LONG).show();

        }
    }

}

//import android.app.WallpaperManager;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import java.io.IOException;
//
//public class MainActivity extends AppCompatActivity {
//
//    Button button;
//    ImageView imageView;
//    WallpaperManager wallpaperManager;
//    Bitmap bitmapBefore, bitmapAfter;
//    DisplayMetrics displayMetrics;
//    int width, height;
//    BitmapDrawable bitmapDrawable;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        button = (Button) findViewById(R.id.button);
//
//        imageView = (ImageView) findViewById(R.id.imageView);
//
//        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
//
//        bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
//
//        bitmapBefore = bitmapDrawable.getBitmap();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                GetScreenWidthHeight();
//
//                SetBitmapSize();
//                wallpaperManager.setWallpaperOffsetSteps(1, 1);
//                wallpaperManager.suggestDesiredDimensions(width, height);
//                //wallpaperManager = WallpaperManager.getInstance(MainActivity.this);
//
//                try {
//
//                    wallpaperManager.setBitmap(bitmapAfter);
//
//                    wallpaperManager.suggestDesiredDimensions(width, height);
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void GetScreenWidthHeight() {
//
//        displayMetrics = new DisplayMetrics();
//
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//        width = imageView.getWidth();
//
//        height = imageView.getHeight();
//
//    }
//
//    public void SetBitmapSize() {
//
//        bitmapAfter = Bitmap.createScaledBitmap(bitmapBefore, width, height, true);
//
//    }
//}
