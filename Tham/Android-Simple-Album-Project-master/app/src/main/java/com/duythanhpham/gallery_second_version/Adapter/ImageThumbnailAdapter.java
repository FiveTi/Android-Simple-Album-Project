package com.duythanhpham.gallery_second_version.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duythanhpham.gallery_second_version.Activities.GalleryImage;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IImageClickedListener;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IImageThumbnailLoader;
import com.duythanhpham.gallery_second_version.Misc.DisplayScreenUtility;
import com.duythanhpham.gallery_second_version.R;

import java.util.ArrayList;

public class ImageThumbnailAdapter extends RecyclerView.Adapter<ImageThumbnailAdapter.ViewHolder>{

    private ArrayList<GalleryImage> galleryList;
    private Context context;

    private IImageThumbnailLoader onImageThumbnailLoader;
    private IImageClickedListener onImageClickedListener;
    private int imageThumbnailWidth;


    public ImageThumbnailAdapter(Context context, ArrayList<GalleryImage> galleryList) {
        this.galleryList = galleryList;
        this.context = context;

        int numOfColumns;
        if (DisplayScreenUtility.isInLandscapeMode(context)) {
            numOfColumns = 4;
        } else {
            numOfColumns = 3;
        }

        imageThumbnailWidth = DisplayScreenUtility.getScreenWidth(context) / numOfColumns;
    }

    @Override
    public ImageThumbnailAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_thumbnail_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageThumbnailAdapter.ViewHolder viewHolder, int i) {
        //viewHolder.title.setText(galleryList.get(i).getImage_title());

        onImageThumbnailLoader.LoadImageThumbnail(viewHolder.img, galleryList.get(i).getImage_ID(), imageThumbnailWidth );

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imagePosition = viewHolder.getAdapterPosition();
                if(imagePosition != RecyclerView.NO_POSITION){
                    onImageClickedListener.OnImageClick(imagePosition);
                }
            }
        });

        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imagePosition = viewHolder.getAdapterPosition();
                if(imagePosition != RecyclerView.NO_POSITION){
                    onImageClickedListener.OnDeleteClick(imagePosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public void setOnImageClickListener(IImageClickedListener listener) {
        this.onImageClickedListener = listener;
    }

    public void setImageThumbnailLoader(IImageThumbnailLoader loader) {
        this.onImageThumbnailLoader = loader;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        //private TextView title;
        private ImageView img, imgDelete;
        public ViewHolder(View view) {
            super(view);

            //title = view.findViewById(R.id.title);
            img = view.findViewById(R.id.img);
            imgDelete = view.findViewById(R.id.image_delete);
        }
    }
}