package com.pic.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pic.R;

public class PhotoViewHolder extends RecyclerView.ViewHolder{

    private ImageView imageView;
    private TextView textView;

    public PhotoViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_view);
        textView = itemView.findViewById(R.id.text_view);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

}
