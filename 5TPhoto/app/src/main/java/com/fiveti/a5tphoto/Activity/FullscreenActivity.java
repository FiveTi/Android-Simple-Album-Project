package com.fiveti.a5tphoto.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fiveti.a5tphoto.Album;
import com.fiveti.a5tphoto.R;

import java.util.ArrayList;

public class FullscreenActivity extends AppCompatActivity {
    public static ArrayList<Album> allPath = new ArrayList<>();
    private String ARRAY_PATH = "array_path";
    int posImage;
    int position;
    ImageView fullImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage_activity);

        fullImage = (ImageView)findViewById(R.id.fullImage);

        Bundle bFullImage = this.getIntent().getExtras();
        allPath = (ArrayList<Album>)bFullImage.getSerializable(ARRAY_PATH);
        position = bFullImage.getInt("posAlbum");
        posImage = bFullImage.getInt("posImage");

        Glide.with(fullImage.getContext()).load("file://" + allPath.get(position).getAllImagePath().get(posImage))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(fullImage);

    }
}
