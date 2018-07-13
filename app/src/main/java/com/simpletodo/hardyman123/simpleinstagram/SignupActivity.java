package com.simpletodo.hardyman123.simpleinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {


    private EditText createUsernameInput;
    private EditText createPasswordInput;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        createUsernameInput = findViewById(R.id.etCreateUser);
        createPasswordInput = findViewById(R.id.etCreatePassword);
        signupBtn = findViewById(R.id.bRegister);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String newUsername = createUsernameInput.getText().toString();
                final String newPassword = createPasswordInput.getText().toString();

                signUp(newUsername, newPassword);
            }
        });





    }



    private void signUp(String username, String password){

        // Create the parse user
        ParseUser user = new ParseUser();
        // Set its core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    // Hooray! Let them use the app now
                    Log.d("SignupActivity", "Congrats, user registered successfully!");
                    // Send user back to login screen
                    goBack();

                } else {
                    // Signup didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e("SignupActivity", "Registration failed");
                    e.printStackTrace();
                }
            }
        });

    }

    public void goBack(){
        // closes the activity and returns to login screen
        this.finish();
    }


}
