package com.example.yummychina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yummychina.model.Post;
import com.example.yummychina.activity.PostDetailActivity;
import com.example.yummychina.adapter.holder.PostViewHolder;
import com.example.yummychina.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * This class bridge PostViewHolder and view: single-post layout
 * The data transitited by PostDetail Activity
 */
public class PostAdapter extends ArrayAdapter<Post> {

    Context context;
    // transitited by PostDetail Activity
    List<Post> posts;

    public PostAdapter(@NonNull Context context, List<Post> posts) {
        super(context, R.layout.single_post, posts);
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Post post = posts.get(position);
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
        holder.getFromWhom().setText(post.getFromWhom());
        Picasso.get().load(post.getImageLink()).into(holder.getImage());
        holder.getDescription().setText(post.getDescription());

        //load data from firebase and arrange into single-post
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("fromWhom", post.getFromWhom());
                intent.putExtra("imageLink", post.getImageLink());
                intent.putExtra("description", post.getDescription());
                intent.putExtra("postId", post.getPostId());
                intent.putExtra("story", post.getStory());
                context.startActivity(intent);
            }
        });
        return singleItem;
    }
}