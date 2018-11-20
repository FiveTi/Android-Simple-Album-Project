package com.example.yagami.navigationlayout.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yagami.navigationlayout.Activity.MainActivity;
import com.example.yagami.navigationlayout.ImagePath;
import com.example.yagami.navigationlayout.R;

import java.util.ArrayList;

public class FullImageFragment extends Fragment {
    public static ArrayList<ImagePath> allPath = new ArrayList<>();
    private String ARRAY_PATH = "array_path";
    int posImage;
    int position;
    ImageView fullImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fullimage_activity, container, false);

        fullImage = (ImageView)v.findViewById(R.id.fullImage);

        Bundle bFullImage = getArguments();
        allPath = (ArrayList<ImagePath>)bFullImage.getSerializable(ARRAY_PATH);
        position = bFullImage.getInt("position");
        posImage = bFullImage.getInt("posImage");

        Glide.with(fullImage.getContext()).load("file://" + allPath.get(position).getAllImagePath().get(posImage))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(fullImage);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fullimage_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
