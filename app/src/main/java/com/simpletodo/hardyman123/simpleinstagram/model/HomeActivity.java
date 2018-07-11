package com.simpletodo.hardyman123.simpleinstagram.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.simpletodo.hardyman123.simpleinstagram.R;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private EditText descriptionInput;
    private Button createButton;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        descriptionInput = findViewById(R.id.etDescription);
        createButton = findViewById(R.id.bCreate);
        refreshButton = findViewById(R.id.bRefresh);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description = descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();

                // final File file = new File(imagePath)
                // this is where we ask the user to select of a file or take a picture with the camera

                // final ParseFile parseFile = new ParseFile(file);
                // pass in the picture file

                // createPost(description, parseFile, user);
            }
        });


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTopPosts();
            }
        });

    }



    private void createPost(String description, ParseFile imageFile, ParseUser user){
            // TODO - create and save post

        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imageFile);
        newPost.setUser(user);

        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.d("HomeActivity", "Create post success!");
                } else {
                    e.printStackTrace();
                }
            }
        });

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

    }
