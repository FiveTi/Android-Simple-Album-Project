package com.duythanhpham.gallery_firstversion.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duythanhpham.gallery_firstversion.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ThumnailImageAdapter extends RecyclerView.Adapter<ThumnailImageAdapter.ViewHolder> {

    Context context;
    List<String> imageList;

    public ThumnailImageAdapter(Context context,List<String> imageList){
        this.context = context;
        this.imageList = imageList;
    }

    //Chạy thứ 2
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgPlayer);
        }
    }

    //Chạy đầu tiên
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_thumnail,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //Chay thứ 3
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load("https://images.unsplash.com/photo-1429152937938-07b5f2828cdd?q=80&amp;fm=jpg&amp;s=a4f424db0ae5a398297df5ae5e0520d6")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.hihi)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
