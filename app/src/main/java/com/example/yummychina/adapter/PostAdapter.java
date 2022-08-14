package com.example.yummychina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yummychina.view.PostDetailActivity;
import com.example.yummychina.model.PostViewHolder;
import com.example.yummychina.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> fromWhims;
    List<String> imageLinks;
    List<String> descriptions;
    List<String> postIds;
    List<String> stories;

    public PostAdapter(@NonNull Context context, List<String> fromWhims, List<String> imageLinks, List<String> descriptions, List<String> postIds, List<String> stroies) {
        super(context, R.layout.single_post, R.id.fromWhom, fromWhims);
        this.context = context;
        this.fromWhims = fromWhims;
        this.imageLinks = imageLinks;
        this.descriptions = descriptions;
        this.postIds = postIds;
        this.stories = stroies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singleItem = convertView;
        PostViewHolder holder;
        if (singleItem == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_post, parent, false);
            holder = new PostViewHolder(singleItem);
            singleItem.setTag(holder);
        } else {
            holder = (PostViewHolder) singleItem.getTag();
        }
        holder.getFromWhom().setText(fromWhims.get(position));
        Picasso.get().load(imageLinks.get(position)).into(holder.getImage());
        holder.getDescription().setText(descriptions.get(position));
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("fromWhom", fromWhims.get(position));
                intent.putExtra("imageLink", imageLinks.get(position));
                intent.putExtra("description", descriptions.get(position));
                intent.putExtra("postId", postIds.get(position));
                intent.putExtra("story", stories.get(position));
                context.startActivity(intent);
            }
        });
        return singleItem;
    }
}