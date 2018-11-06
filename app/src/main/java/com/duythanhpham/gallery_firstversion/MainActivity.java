package com.duythanhpham.gallery_firstversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.duythanhpham.gallery_firstversion.Activities.ThumnailImageActivity;
import com.duythanhpham.gallery_firstversion.Adapters.ThumnailImageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ThumnailImageAdapter  mRcvAdapter;
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
        List<String> dulieu = new ArrayList<>();

        String[] images = getResources().getStringArray(R.array.unsplash_images);
        for (int i=0 ;i<images.length;i++){
            dulieu.add(images[i]);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3);
        mRcvAdapter = new ThumnailImageAdapter(this,dulieu);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mRcvAdapter);

        mRcvAdapter.notifyDataSetChanged();
    }
}
