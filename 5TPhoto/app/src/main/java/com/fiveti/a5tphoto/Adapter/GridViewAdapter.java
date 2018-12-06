package com.fiveti.a5tphoto.Adapter;

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
import com.fiveti.a5tphoto.ImagePath;
import com.fiveti.a5tphoto.R;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<ImagePath> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagePath> allPath = new ArrayList<>();
    int int_position;


    public GridViewAdapter(Context context, ArrayList<ImagePath> al_menu,int int_position) {
        super(context, R.layout.grid_image_layout, al_menu);
        this.allPath = al_menu;
        this.context = context;
        this.int_position = int_position;


    }

    @Override
    public int getCount() {

        Log.e("ADAPTER LIST SIZE", allPath.get(int_position).getAllImagePath().size() + "");
        return allPath.get(int_position).getAllImagePath().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (allPath.get(int_position).getAllImagePath().size() > 0) {
            return allPath.get(int_position).getAllImagePath().size();
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_image_layout, parent, false);
            viewHolder.folder = (TextView) convertView.findViewById(R.id.txtFolder);
            viewHolder.number = (TextView) convertView.findViewById(R.id.txtNumber);
            viewHolder.imageAlbum = (ImageView) convertView.findViewById(R.id.imgAlbum);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.folder.setVisibility(View.GONE);
        viewHolder.number.setVisibility(View.GONE);



        Glide.with(context).load("file://" + allPath.get(int_position).getAllImagePath().get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(viewHolder.imageAlbum);


        return convertView;

    }

    private static class ViewHolder {
        TextView folder, number;
        ImageView imageAlbum;
    }
}
