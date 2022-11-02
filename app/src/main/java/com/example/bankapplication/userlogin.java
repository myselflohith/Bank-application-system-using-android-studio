package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class userlogin extends AppCompatActivity {
    Button createbtn, loginbtn;
    EditText username, password;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        getSupportActionBar().setTitle("Login");
        Button createbtn = findViewById(R.id.createbtn);
        Button loginbtn = findViewById(R.id.loginbtn);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        createbtn = (Button) findViewById(R.id.createbtn);

        DB = new DBHelper(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("")||pass.equals("")) {
                    Toast.makeText(userlogin.this, "Please Enter the Credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean result = DB.checkusernamepassword(user, pass);
                    if (result == true) {
                        Toast.makeText(userlogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(userlogin.this, dashboard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(userlogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userlogin.this, profileform.class);
                startActivity(intent);
                finish();
            }
        });
    }
}