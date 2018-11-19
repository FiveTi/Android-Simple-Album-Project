package com.example.yagami.navigationlayout.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.yagami.navigationlayout.Activity.MainActivity;
import com.example.yagami.navigationlayout.Adapter.AlbumAdapter;
import com.example.yagami.navigationlayout.ImagePath;
import com.example.yagami.navigationlayout.R;

import java.util.ArrayList;

public class AlbumFragment extends Fragment {

    AlbumAdapter adapter;
    GridView gvAlbum;

    public static ArrayList<ImagePath> allPath = new ArrayList<>();
    private String ARRAY_PATH = "array_path";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album, container, false);

        gvAlbum = (GridView)v.findViewById(R.id.gridViewAlbum);

        Bundle bAlbum = getArguments();
        allPath = (ArrayList<ImagePath>)bAlbum.getSerializable(ARRAY_PATH);

        adapter = new AlbumAdapter(v.getContext(),allPath);
        gvAlbum.setAdapter(adapter);

        gvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            GalleryFragment galleryFragment = new GalleryFragment();
            Bundle bGallery = new Bundle();
            bGallery.putSerializable(ARRAY_PATH, allPath);
            bGallery.putInt("position", i);
            galleryFragment.setArguments(bGallery);
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, galleryFragment).addToBackStack("gallery").commit();
            }
        });

        return v;
    }

}
