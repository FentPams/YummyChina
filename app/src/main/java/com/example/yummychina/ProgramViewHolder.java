package com.example.yummychina;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder {

    ImageView image;
    TextView fromWhom;
    TextView description;

    public ProgramViewHolder(View v) {
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
