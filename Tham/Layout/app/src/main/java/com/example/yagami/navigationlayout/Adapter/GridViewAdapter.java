package com.example.yagami.navigationlayout.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yagami.navigationlayout.ImagePath;
import com.example.yagami.navigationlayout.R;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<ImagePath> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagePath> al_menu = new ArrayList<>();
    int int_position;


    public GridViewAdapter(Context context, ArrayList<ImagePath> al_menu,int int_position) {
        super(context, R.layout.fullscreen_image_layout, al_menu);
        this.al_menu = al_menu;
        this.context = context;
        this.int_position = int_position;


    }

    @Override
    public int getCount() {

        Log.e("ADAPTER LIST SIZE", al_menu.get(int_position).getAllImagePath().size() + "");
        return al_menu.get(int_position).getAllImagePath().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (al_menu.get(int_position).getAllImagePath().size() > 0) {
            return al_menu.get(int_position).getAllImagePath().size();
        } else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fullscreen_image_layout, parent, false);
            viewHolder.tv_foldern = (TextView) convertView.findViewById(R.id.txtFolder);
            viewHolder.tv_foldersize = (TextView) convertView.findViewById(R.id.txtNumber);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.imgAlbum);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_foldern.setVisibility(View.GONE);
        viewHolder.tv_foldersize.setVisibility(View.GONE);



        Glide.with(context).load("file://" + al_menu.get(int_position).getAllImagePath().get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(viewHolder.iv_image);


        return convertView;

    }

    private static class ViewHolder {
        TextView tv_foldern, tv_foldersize;
        ImageView iv_image;


    }


}
