package com.example.yummychina.model;

import android.view.View;
import android.widget.TextView;

import com.example.yummychina.R;

public class CommentHolder {
    TextView fromWhom;
    TextView comment;
    TextView date;

    public CommentHolder(View v) {
        fromWhom = v.findViewById(R.id.fromWhom);
        comment = v.findViewById(R.id.comment);
        date = v.findViewById(R.id.date);
    }

    public TextView getFromWhom() {
        return fromWhom;
    }

    public TextView getComment() {
        return comment;
    }

    public TextView getDate() {
        return date;
    }
}
