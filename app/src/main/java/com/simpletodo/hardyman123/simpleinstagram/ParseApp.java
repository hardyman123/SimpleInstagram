package com.simpletodo.hardyman123.simpleinstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.simpletodo.hardyman123.simpleinstagram.model.Post;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("ari-peralta")
                .clientKey("marinko")
                .server("http://hardyman123-fbu-instagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
