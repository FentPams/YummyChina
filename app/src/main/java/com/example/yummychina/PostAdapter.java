package com.example.yummychina;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> fromWhims;
    List<String> imageLinks;
    List<String> descriptions;
    List<String> postIds;

    public PostAdapter(@NonNull Context context, List<String> fromWhims, List<String> imageLinks, List<String> descriptions, List<String> postIds) {
        super(context, R.layout.single_post, R.id.fromWhom, fromWhims);
        this.context = context;
        this.fromWhims = fromWhims;
        this.imageLinks = imageLinks;
        this.descriptions = descriptions;
        this.postIds = postIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singleItem = convertView;
        ProgramViewHolder holder;
        if (singleItem == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_post, parent, false);
            holder = new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        } else {
            holder = (ProgramViewHolder) singleItem.getTag();
        }
        holder.fromWhom.setText(fromWhims.get(position));
        Picasso.get().load(imageLinks.get(position)).into(holder.image);
        holder.description.setText(descriptions.get(position));
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("fromWhom", fromWhims.get(position));
                intent.putExtra("imageLink", imageLinks.get(position));
                intent.putExtra("description", descriptions.get(position));
                intent.putExtra("postId", postIds.get(position));
                context.startActivity(intent);
            }
        });
        return singleItem;
    }
}