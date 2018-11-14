package com.duythanhpham.gallery_second_version;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duythanhpham.gallery_second_version.Activities.FullScreenImageActivity;
import com.duythanhpham.gallery_second_version.Activities.GalleryImage;
import com.duythanhpham.gallery_second_version.Activities.ImageThumbnailActivity;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IFullScreenImageLoader;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IImageClickedListener;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IImageThumbnailLoader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IImageThumbnailLoader, IFullScreenImageLoader {
    //region attributes
    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img5",
            "Img6",
            "Img7",
            "Img8",
            "Img9",
            "Img10",
            "Img11",
            "Img12",
            "Img13",
    };

    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
    };
    //endregion

    Button btnMoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnMoApp = findViewById(R.id.MoApp);

        btnMoApp.setOnClickListener(this);
        ImageThumbnailActivity.setImageThumbnailLoader(this);
        FullScreenImageActivity.setFullScreenImageLoader(this);
    }

    private ArrayList<GalleryImage> prepareData() {

        ArrayList<GalleryImage> theimage = new ArrayList<>();
        for (int i = 0; i < image_titles.length; i++) {
            GalleryImage galleryImage = new GalleryImage();
            galleryImage.setImage_title(image_titles[i]);
            galleryImage.setImage_ID(image_ids[i]);
            theimage.add(galleryImage);
        }
        return theimage;
    }

    @Override
    public void onClick(View v) {
        Intent GalleryApp = new Intent(MainActivity.this, ImageThumbnailActivity.class);

        ArrayList<GalleryImage> galleryImages = prepareData();
//        Bundle extras = new Bundle();
//        extras.putSerializable(ImageThumbnailActivity.KEY_IMAGE_LIST, galleryImages);

        GalleryApp.putExtra(ImageThumbnailActivity.KEY_IMAGE_LIST, galleryImages);
        startActivity(GalleryApp);
    }

    @Override
    public void LoadImageThumbnail(ImageView iv, Integer imageID, int dimension) {
        Picasso.with(iv.getContext())
                .load(imageID)
                .resize(dimension, dimension)
                .centerCrop()
                .into(iv);
    }

    @Override
    public void LoadFullScreenImage(ImageView iv, Integer imageID, int width, LinearLayout bglinearLayout) {

        Picasso.with(iv.getContext())
                .load(imageID)
                .resize(width, width)
                .centerCrop()
                .into(iv);

    }


//    String path = Environment.getRootDirectory().toString();
//    File f = new File(path);
//    File file[] = f.listFiles();
//for (int i=0; i < file.length; i++)
//    {
//        CreateList createList = new CreateList();
//        createList.setImage_Location(file[i].getName());
//    }
}
