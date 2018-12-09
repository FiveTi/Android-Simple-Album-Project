package com.fiveti.a5tphoto.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
//import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fiveti.a5tphoto.Adapter.GridViewAdapter;
import com.fiveti.a5tphoto.Album;
import com.fiveti.a5tphoto.R;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    GridView gvAlbum;
    GridViewAdapter adapter;

    private String ARRAY_PATH = "array_path";
    private ArrayList<Album> all_images_path = new ArrayList<>();
    int posAlbum;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);

        gvAlbum = (GridView)findViewById(R.id.gridViewGallery);

        Bundle bGallery = this.getIntent().getExtras();
        all_images_path = (ArrayList<Album>) bGallery.getSerializable(ARRAY_PATH);
        posAlbum = bGallery.getInt("posAlbum");

        adapter = new GridViewAdapter(this, all_images_path, posAlbum);
        gvAlbum.setAdapter(adapter);
        gvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Gửi vị trí ảnh hiện tại, tên ảnh và cả mảng file
                showAlbum(i);
            }
        });

        actionBar = getSupportActionBar();
        if( actionBar != null){
            actionBar.setTitle(all_images_path.get(posAlbum).getFolder());
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Nếu click vào nút back
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return false;
    }

    public void showAlbum(int posImage)
    {
        Intent iFullImage = new Intent(this, FullscreenImageActivity.class);
        Bundle bFullImage = new Bundle();
        bFullImage.putSerializable(ARRAY_PATH, all_images_path);
        bFullImage.putInt("posAlbum", posAlbum);
        bFullImage.putInt("posImage", posImage);
        iFullImage.putExtras(bFullImage);
        startActivity(iFullImage);
    }
}