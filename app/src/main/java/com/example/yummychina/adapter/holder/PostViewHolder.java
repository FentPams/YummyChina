package com.example.yummychina.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yummychina.R;
/**
 * This class is view holder for single Post
 *
 * The according layout:single_post.xml
 */
public class PostViewHolder {

    ImageView image;
    TextView fromWhom;
    TextView description;

    public PostViewHolder(View v) {
        image = v.findViewById(R.id.imageView);
        fromWhom = v.findViewById(R.id.fromWhom);
        description = v.findViewById(R.id.description);
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getFromWhom() {
        return fromWhom;
    }

    public TextView getDescription() {
        return description;
    }
}
