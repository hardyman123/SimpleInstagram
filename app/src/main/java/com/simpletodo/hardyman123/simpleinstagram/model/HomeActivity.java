package com.simpletodo.hardyman123.simpleinstagram.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.simpletodo.hardyman123.simpleinstagram.FeedActivity;
import com.simpletodo.hardyman123.simpleinstagram.LoginActivity;
import com.simpletodo.hardyman123.simpleinstagram.PostActivity;
import com.simpletodo.hardyman123.simpleinstagram.R;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Button refreshButton;
    private Button logoutButton;
    private Button makePostButton;
    private Button feedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        refreshButton = findViewById(R.id.bRefresh);
        logoutButton = findViewById(R.id.bLogOut);
        makePostButton = findViewById(R.id.bMakePost);
        feedButton = findViewById(R.id.bFeed);





        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("button is working");
                loadTopPosts();
            }
        });


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Logout button is working");
                logout();
                System.out.println(ParseUser.getCurrentUser());
                System.out.println("we outta here");
            }
        });

        makePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePost();
                System.out.println("Post button works");
            }
        });

        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeed();
            }
        });

    }


    private void openFeed(){
        final Intent intent = new Intent(HomeActivity.this, FeedActivity.class);
        startActivity(intent);
    }



    private void loadTopPosts(){
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();

        postsQuery.findInBackground(new FindCallback<Post>() {
                @Override
                public void done(List<Post> objects, ParseException e) {
                    if(e == null){
                        for(int i = 0; i < objects.size(); i++){
                            Log.d("HomeActivity", "Post[" + i + "] = "
                                    + objects.get(i).getDescription()
                                    + "\nusername = " + objects.get(i).getUser().getUsername());

                        }
                    } else {
                        e.printStackTrace();
                    }
                }
            });
        }


        private void logout(){
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser(); // this should be null now

            final Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }


        public void makePost(){
            final Intent intent = new Intent(HomeActivity.this, PostActivity.class);
            startActivity(intent);
        }

    }
