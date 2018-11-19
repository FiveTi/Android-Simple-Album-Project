package com.example.yagami.navigationlayout.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.yagami.navigationlayout.Adapter.GridViewAdapter;
import com.example.yagami.navigationlayout.ImagePath;
import com.example.yagami.navigationlayout.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    GridView gvAlbum;
    GridViewAdapter adapter;

    private String ARRAY_PATH = "array_path";
    public static ArrayList<ImagePath> allPath = new ArrayList<>();
    public static ArrayList<ImagePath> allPathGalery = new ArrayList<>();
    int position;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        gvAlbum = (GridView) v.findViewById(R.id.gridViewGallery);

        Bundle bGallery = getArguments();
        allPath = (ArrayList<ImagePath>)bGallery.getSerializable(ARRAY_PATH);
        position = bGallery.getInt("position");

        if(position == -1) {
            ArrayList<String> allImagePath = new ArrayList<>();

            for (int i = 0; i < allPath.size(); i++) {
                for (int j = 0; j < allPath.get(i).getAllImagePath().size(); j++) {
                    allImagePath.add(allPath.get(i).getAllImagePath().get(j));
                }
            }

            ImagePath obj = new ImagePath();
            obj.setFolder(allPath.get(0).getFolder());
            obj.setAllImagePath(allImagePath);

            allPathGalery.add(obj);

            adapter = new GridViewAdapter(v.getContext(),allPathGalery,0);
        }
        else
        {
            adapter = new GridViewAdapter(v.getContext(),allPath,position);
        }
        gvAlbum.setAdapter(adapter);
        return v;
    }
}

