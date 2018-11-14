package com.duythanhpham.gallery_second_version.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duythanhpham.gallery_second_version.Adapter.Interface.IFullScreenImageLoader;
import com.duythanhpham.gallery_second_version.R;

import java.util.ArrayList;
import java.util.List;

public class FullScreenImageActivity extends AppCompatActivity implements IFullScreenImageLoader {
    // region Constants
    public static final String KEY_IMAGE_LIST = "KEY_IMAGE_LIST";
    public static final String KEY_POSITION = "KEY_POSITION";
    // endregion

    // region Member Variables
    private List<GalleryImage> imageList;
    private int position;
    private static IFullScreenImageLoader iFullScreenImageLoader;
    GestureDetector gestureDetector;
    ImageView imgFullscreen;
    int SWIPE_THRESHOLD = 100;
    int SWIPE_VELOCITY_THRESHOLD = 100;
    // endregion


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_image_layout);

        imgFullscreen = (ImageView)findViewById(R.id.fullscreen_image);

        getSupportActionBar().setTitle("FullScreenImageActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            imageList = (ArrayList<GalleryImage>) intent.getSerializableExtra(KEY_IMAGE_LIST);

            Bundle bundle = intent.getExtras();
            position = bundle.getInt(KEY_POSITION);
        }

        imgFullscreen.setImageResource(imageList.get(position).getImage_ID());
        gestureDetector = new GestureDetector(this, new MyGesture());
        imgFullscreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    // lang nghe cac cu chi
    class MyGesture extends GestureDetector.SimpleOnGestureListener
    {
        //cu chi vuot

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //vuot tu trai sang phai
            if(e2.getX() - e1.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
            {
                position --;
                if(position < 0)
                {
                    position = imageList.size() - 1;
                }
                imgFullscreen.setImageResource(imageList.get(position).getImage_ID());
            }
            //vuot tu phai sang trai
            if(e1.getX() - e2.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
            {
                position ++;
                if(position > imageList.size() - 1)
                {
                    position = 0;
                }
                imgFullscreen.setImageResource(imageList.get(position).getImage_ID());
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fullscreen_image, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        imgFullscreen = (ImageView)findViewById(R.id.fullscreen_image);
        switch (item.getItemId())
        {
            case R.id.menu_Info:

                break;
            case R.id.menu_Delete:

                break;
            case R.id.menu_Edit:

                break;
            case R.id.menu_Rotate:
                imgFullscreen.setRotation(imgFullscreen.getRotation() + 90);
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void setFullScreenImageLoader(IFullScreenImageLoader loader) {
        iFullScreenImageLoader = loader;
    }
}
