package com.example.yagami.navigationlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.yagami.navigationlayout.Adapter.GridViewAdapter;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    GridView gvAlbum;
    GridViewAdapter adapter;

    private String ARRAY_PATH = "array_path";
    public static ArrayList<ImagePath> allPath = new ArrayList<>();
    public static ArrayList<ImagePath> allPathGalery = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        Bundle bundle = getArguments();
        allPath = (ArrayList<ImagePath>)bundle.getSerializable(ARRAY_PATH);

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

        gvAlbum = (GridView)v.findViewById(R.id.gridViewAlbum);
        adapter = new GridViewAdapter(v.getContext(),allPathGalery,0);
        gvAlbum.setAdapter(adapter);
        return v;
    }
}
