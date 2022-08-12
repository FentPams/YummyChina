package com.example.yummychina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yummychina.PostDetailActivity;
import com.example.yummychina.ProgramViewHolder;
import com.example.yummychina.R;
import com.example.yummychina.model.CommentHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> fromWhims;
    List<String> comments;
    List<String> dates;

    public CommentAdapter(@NonNull Context context, List<String> fromWhims, List<String> comments, List<String> dates) {
        super(context, R.layout.single_comment, R.id.fromWhom, fromWhims);
        this.context = context;
        this.fromWhims = fromWhims;
        this.comments = comments;
        this.dates = dates;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singleItem = convertView;
        CommentHolder holder;
        if (singleItem == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_comment, parent, false);
            holder = new CommentHolder(singleItem);
            singleItem.setTag(holder);
        } else {
            holder = (CommentHolder) singleItem.getTag();
        }
        holder.getFromWhom().setText(fromWhims.get(position));
        holder.getComment().setText(comments.get(position));
        holder.getDate().setText(dates.get(position));
        return singleItem;
    }
}