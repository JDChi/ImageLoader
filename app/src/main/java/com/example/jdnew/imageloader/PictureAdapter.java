package com.example.jdnew.imageloader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by JDNew on 2017/8/13.
 */

public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private RootImgData mData;
    private ImageLoader imageLoader;

    public PictureAdapter(Context context , RootImgData data){
    mContext = context;
        mData = data;
        imageLoader = new ImageLoader(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_img , null);
        return new ImgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImgViewHolder imgViewHolder = (ImgViewHolder) holder;
        imageLoader.bindBitmap(mData.getEntries().get(position).getImages().getLarge()
        , imgViewHolder.imageView , 500 , 500);

    }

    @Override
    public int getItemCount() {
        return mData.getTotal();
    }

    private class ImgViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public ImgViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
