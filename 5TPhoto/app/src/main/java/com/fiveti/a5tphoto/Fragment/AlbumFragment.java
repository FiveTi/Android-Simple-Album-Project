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

import com.fiveti.a5tphoto.Activity.GalleryActivity;
import com.fiveti.a5tphoto.Activity.MainActivity;
import com.fiveti.a5tphoto.Adapter.AlbumAdapter;
import com.fiveti.a5tphoto.Database.Album;
import com.fiveti.a5tphoto.R;

import java.util.ArrayList;

public class AlbumFragment extends Fragment {

    AlbumAdapter adapter;
    GridView gvAlbum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album, container, false);

        gvAlbum = (GridView)v.findViewById(R.id.gridViewAlbum);

        adapter = new AlbumAdapter(v.getContext(), MainActivity.all_images_path);
        gvAlbum.setAdapter(adapter);

        gvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent iAlbum = new Intent(getActivity(), GalleryActivity.class);
                //Gửi vị trí ảnh hiện tại
                Bundle bGallery = new Bundle();
                bGallery.putInt("posAlbum", i);
                iAlbum.putExtras(bGallery);
                startActivity(iAlbum);
            }
        });

        return v;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
}
