package com.simpletodo.hardyman123.simpleinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.simpletodo.hardyman123.simpleinstagram.model.HomeActivity;
import com.simpletodo.hardyman123.simpleinstagram.model.Post;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private Button homeButton;
    ArrayList<Post> posts;
    RecyclerView rvPosts;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        // initialize Home Button
        homeButton = findViewById(R.id.bGoHome);

        // initialize arraylist
        posts = new ArrayList<>();
        // initialize the adapter -- post array CAN'T be re-initiated after this call
        adapter = new PostAdapter(posts);

        // resolve the recyler view and connect a layout manager and the adapter
        rvPosts = (RecyclerView) findViewById(R.id.rvFeed);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvPosts.setAdapter(adapter);


        // add listener to home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeMeHome();
                //makeItStop();
            }
        });

        getPosts();

    }


    private void getPosts() {

        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // Execute the find asynchronously
        query.findInBackground(new FindCallback<Post>() {
            public void done(List<Post> itemList, ParseException e) {
                if (e == null) {
                    // Access the array of results here
                   posts.clear();
                   posts.addAll(itemList);
                   adapter.notifyDataSetChanged();
                } else {
                    Log.d("item", "Error: " + e.getMessage());
                }
            }

        });
    }


    private void takeMeHome() {
        Intent intent = new Intent(FeedActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    private void makeItStop() {
        this.finish();
    }

}

