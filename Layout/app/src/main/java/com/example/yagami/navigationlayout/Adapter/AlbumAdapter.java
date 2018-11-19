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

public class AlbumAdapter extends ArrayAdapter<ImagePath> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagePath> allPath = new ArrayList<>();


    public AlbumAdapter(Context context, ArrayList<ImagePath> al_menu) {
        super(context, R.layout.grid_image_layout, al_menu);
        this.allPath = al_menu;
        this.context = context;


    }

    @Override
    public int getCount() {

        Log.e("ADAPTER LIST SIZE", allPath.size() + "");
        return allPath.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (allPath.size() > 0) {
            return allPath.size();
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

        viewHolder.folder.setText(allPath.get(position).getFolder());
        viewHolder.number.setText(allPath.get(position).getAllImagePath().size()+"");



        Glide.with(context).load("file://" + allPath.get(position).getAllImagePath().get(0))
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
