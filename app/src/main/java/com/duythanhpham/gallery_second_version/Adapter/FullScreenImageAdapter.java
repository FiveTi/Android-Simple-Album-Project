package com.duythanhpham.gallery_second_version.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duythanhpham.gallery_second_version.Activities.GalleryImage;
import com.duythanhpham.gallery_second_version.Adapter.Interface.IFullScreenImageLoader;
import com.duythanhpham.gallery_second_version.Misc.DisplayScreenUtility;
import com.duythanhpham.gallery_second_version.R;

import java.util.List;

public class FullScreenImageAdapter extends PagerAdapter {
    // region Member Variables
    private final List<GalleryImage> images;
    private IFullScreenImageLoader iFullScreenImageLoader;
    private Context context;
    // endregion

    public FullScreenImageAdapter(Context context, List<GalleryImage> images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.fullscreen_image_layout, null);
       View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fullscreen_image_layout, container, false);

        ImageView imageView = view.findViewById(R.id.fullscreen_image);
        final LinearLayout linearLayout = view.findViewById(R.id.ll);

        Integer imageID = images.get(position).getImage_ID();
        Context context = imageView.getContext();
        int ScreenWidth = DisplayScreenUtility.getScreenWidth(context);
        iFullScreenImageLoader.LoadFullScreenImage(imageView, imageID, ScreenWidth, linearLayout);

        container.addView(view, 0);

        return view;
    }

    public void setFullScreenImageLoader(IFullScreenImageLoader loader) {
        this.iFullScreenImageLoader = loader;
    }
}
