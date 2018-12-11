package com.fiveti.a5tphoto.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fiveti.a5tphoto.Activity.FullscreenImageActivity;
import com.fiveti.a5tphoto.Activity.MainActivity;
import com.fiveti.a5tphoto.Adapter.GridViewAdapter;
import com.fiveti.a5tphoto.Database.Album;
import com.fiveti.a5tphoto.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    GridView gvAlbum;
    GridViewAdapter adapter;

    private String ARRAY_PATH = "array_path";
    public static ArrayList<Album> allPathGalery = new ArrayList<>();

    public static int NUM_GRID_COLUMNS=4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        gvAlbum = (GridView) v.findViewById(R.id.gridViewGallery);

        getPathGallery();
        //
        adapter = new GridViewAdapter(v.getContext(), allPathGalery, 0);

        int gridWidth=gvAlbum.getResources().getDisplayMetrics().widthPixels;

        int imageWidth=gridWidth/NUM_GRID_COLUMNS;

        gvAlbum.setColumnWidth(imageWidth);

        gvAlbum.setAdapter(adapter);

        gvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                ShowFullscreenImage(i);
            }
        });

        return v;
    }

    void ShowFullscreenImage(int posImage)
    {
        Intent iFullImage = new Intent(getActivity(), FullscreenImageActivity.class);
        //Gửi vị trí ảnh hiện tại và cả mảng file
        Bundle bFullImage = new Bundle();
        bFullImage.putSerializable(ARRAY_PATH, allPathGalery);
        bFullImage.putInt("posAlbum", 0);
        bFullImage.putInt("posImage", posImage);
        bFullImage.putInt("posAlbumReal", getPosAlbumReal(posImage));
        iFullImage.putExtras(bFullImage);
        startActivity(iFullImage);
    }

    int getPosAlbumReal(int pos)
    {
        int posAlbumReal = 0;
        for(int i = 0; i < MainActivity.all_images_path.size(); i++)
        {
            if(pos > MainActivity.all_images_path.get(i).getAllImagePath().size())
            {
                pos -= MainActivity.all_images_path.get(i).getAllImagePath().size();
                posAlbumReal++;
            }
            else
            {
                break;
            }
        }
        return posAlbumReal;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null){
            allPathGalery.clear();
            getPathGallery();
            adapter.notifyDataSetChanged();
        }
    }

    void getPathGallery()
    {
        // dua het tat ca duong dan hinh anh vao mot thu muc de load vao tab layout gallery
        ArrayList<String> allImagePath = new ArrayList<>();
        for (int i = 0; i < MainActivity.all_images_path.size(); i++) {

            for (int j = 0; j < MainActivity.all_images_path.get(i).getAllImagePath().size(); j++) {

                allImagePath.add(MainActivity.all_images_path.get(i).getAllImagePath().get(j));
            }
        }
        Album obj = new Album();
        obj.setAlbumName(MainActivity.all_images_path.get(0).getAlbumName());
        obj.setAllImagePath(allImagePath);
        allPathGalery.add(obj);
    }
}

