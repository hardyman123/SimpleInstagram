package com.simpletodo.hardyman123.simpleinstagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.simpletodo.hardyman123.simpleinstagram.model.GlideApp;
import com.simpletodo.hardyman123.simpleinstagram.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    // list of posts
    ArrayList<Post> posts;
    // context for rendering
    Context context;


    // initialize with list
    public PostAdapter(ArrayList<Post> posts){
        this.posts = posts;
    }

    // creates and inflates a new view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // get the context and create the inflator
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create the view using the item_post layout
        View postView = inflater.inflate(R.layout.item_post, parent, false);
        // return a new ViewHolder
        return new ViewHolder(postView);
    }

    // binds an inflated view to a new item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the post data at the specified position
        Post post = posts.get(position);
        // populate the view with the movie data
        holder.tvDescription.setText(post.getDescription());
        try {
            holder.tvUsername.setText(post.getUser().fetchIfNeeded().getUsername());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // load image using glide
        GlideApp.with(context).load(post.getImage().getUrl()).into(holder.ivPostImage);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        // track view objects
        ImageView ivPostImage;
        TextView tvUsername;
        TextView tvDescription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // lookup view objects by id
            ivPostImage = (ImageView) itemView.findViewById(R.id.ivFeedPicture);
            tvUsername = (TextView) itemView.findViewById(R.id.tvPostUser);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
        }
    }


}
