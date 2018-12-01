package com.example.yagami.navigationlayout.Fragment;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.yagami.navigationlayout.Activity.MainActivity;
import com.example.yagami.navigationlayout.Activity.openCamera;
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

        gvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if(position == -1) {
                    position = 0;
                    for (int j = 0; j < allPath.size(); j++) {
                        if (i >= allPath.get(j).getAllImagePath().size()) {
                            i = i - allPath.get(j).getAllImagePath().size();
                            position = j + 1;
                        } else {
                            j = allPath.size();
                        }
                    }
                }
                FullImageFragment fullImageFragment = new FullImageFragment();
                Bundle bFullImage = new Bundle();
                bFullImage.putSerializable(ARRAY_PATH, allPath);
                bFullImage.putInt("position", position);
                bFullImage.putInt("posImage", i);
                fullImageFragment.setArguments(bFullImage);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fullImageFragment).addToBackStack("fullImage").commit();
            }
        });

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId())
        {
            case R.id.nav_camera:
                Intent camera=new Intent(getActivity(),openCamera.class);
                startActivity(camera);
                break;
            case R.id.nav_edit:
                break;
            case R.id.nav_share:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

