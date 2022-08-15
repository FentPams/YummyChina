package com.example.yummychina.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yummychina.R;
import com.example.yummychina.adapter.holder.CommentHolder;
import com.example.yummychina.model.Comment;

import java.util.List;
// This class bridge the model:CommentHolder and view: single-comment layout
public class CommentAdapter extends ArrayAdapter<Comment> {

    Context context;
    //Transited by PostDetailActivity
    List<Comment> comments;

    public CommentAdapter(@NonNull Context context, List<Comment> comments) {
        super(context, R.layout.single_comment, comments);
        this.context = context;
        this.comments = comments;
    }

    // Sets data into the view:single item layout
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment comment = comments.get(position);
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
        holder.getFromWhom().setText(comment.getFromWhom());
        holder.getComment().setText(comment.getContent());
        holder.getDate().setText(comment.getDateToString());
        return singleItem;
    }
}