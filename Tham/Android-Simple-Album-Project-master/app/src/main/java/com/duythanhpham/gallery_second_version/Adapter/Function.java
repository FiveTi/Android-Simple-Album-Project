package com.duythanhpham.gallery_second_version.Adapter;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.duythanhpham.gallery_second_version.Activities.FullScreenImageActivity;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IFullScreenImageLoader;
import com.duythanhpham.gallery_second_version.R;

public class Function extends FullScreenImageActivity {
    private View view;
    ImageView imgView;

    public Function()
    {

    }

    public Function(View v, ImageView imgV)
    {
        view = v;
        imgView = imgV;
    }



    public void RotateImage()
    {
        final Button rotateButton = view.findViewById(R.id.RotateButton);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setRotation(imgView.getRotation() + 90);
            }
        });
    }

    public void Zoom()
    {
        final ScaleGestureDetector gesture;
        gesture = new ScaleGestureDetector(view.getContext(), new MyGesture());
        imgView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gesture.onTouchEvent(event);
                return true;
            }
        });
    }

    class MyGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        float scale = 1.0F, onScaleStart = 0, onScaleEnd = 0;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            imgView.setScaleX(scale);
            imgView.setScaleY(scale);
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            onScaleStart = scale;
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            onScaleEnd = scale;
            super.onScaleEnd(detector);
        }
    }

}