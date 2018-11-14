package com.duythanhpham.gallery_second_version.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.duythanhpham.gallery_second_version.Adapter.ImageThumbnailAdapter;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IImageClickedListener;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IImageThumbnailLoader;
import com.duythanhpham.gallery_second_version.R;

import java.util.ArrayList;

public class ImageThumbnailActivity extends AppCompatActivity implements IImageClickedListener, IImageThumbnailLoader {
    // region Constants
    public static final String KEY_IMAGE_LIST = "KEY_IMAGE_LIST";
    public static final Integer numOfColumns = 3;
    // endregion

    // region Views
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    ImageThumbnailAdapter imageThumbnailAdapter;
    // endregion

    // region Member Variables
    private ArrayList<GalleryImage> imageList;
    private static IImageThumbnailLoader onImageThumbnailLoader;
    // endregion


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_thumbnail_activity);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.image_thumbnail_recyclerview);
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        if (intent != null) {
            imageList = (ArrayList<GalleryImage>) intent.getSerializableExtra(KEY_IMAGE_LIST);
        }

        SetUpRecyclerView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        SetUpRecyclerView();
    }

    public void SetUpRecyclerView()
    {
        recyclerView.setLayoutManager(new GridLayoutManager(ImageThumbnailActivity.this, numOfColumns));
        imageThumbnailAdapter = new ImageThumbnailAdapter(this, imageList);
        imageThumbnailAdapter.setOnImageClickListener(this);
        imageThumbnailAdapter.setImageThumbnailLoader(this);
        imageThumbnailAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(imageThumbnailAdapter);
    }

    @Override
    public void OnImageClick(int position) {
        Intent intent = new Intent(ImageThumbnailActivity.this, FullScreenImageActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt(FullScreenImageActivity.KEY_POSITION, position);

        intent.putExtras(bundle);
        intent.putExtra(FullScreenImageActivity.KEY_IMAGE_LIST, imageList);

        startActivity(intent);
    }

    @Override
    public void OnDeleteClick(int position) {
        removeItem(position );
    }

    public void removeItem(int position) {
        imageList.remove(position);
        imageThumbnailAdapter.notifyItemRemoved(position);
    }

    @Override
    public void LoadImageThumbnail(ImageView iv, Integer imageID, int dimension) {
        onImageThumbnailLoader.LoadImageThumbnail(iv, imageID, dimension);
    }

    public static void setImageThumbnailLoader(IImageThumbnailLoader loader) {
        onImageThumbnailLoader = loader;
    }
}
