package com.simpletodo.hardyman123.simpleinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.simpletodo.hardyman123.simpleinstagram.model.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameInput = findViewById(R.id.etUsername);
        passwordInput = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.bLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();
                login(username, password);
            }
        });

    }

    private void login(String username, String password){
        // TODO - do later

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){
                    Log.d("LoginActivity", "Login Successful!");
                    final Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("LoginActivity", "Login Failure");
                    e.printStackTrace();
                }
            }
        });

    }

}