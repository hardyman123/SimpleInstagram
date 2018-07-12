package com.simpletodo.hardyman123.simpleinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                final String username = createUsernameInput.getText().toString();
                final String password = createPasswordInput.getText().toString();
            }
        });





    }






}
