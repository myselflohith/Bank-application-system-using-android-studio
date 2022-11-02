package com.example.bankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class profileform extends AppCompatActivity {
    EditText  user, pass,repass;
    Button cbtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileform);
        getSupportActionBar().setTitle("Create Profile");

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        repass = (EditText) findViewById(R.id.repass);
        cbtn = (Button) findViewById(R.id.cbtn);

        DB = new DBHelper(this);

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = user.getText().toString();
                String ps = pass.getText().toString();
                String rep = repass.getText().toString();

                if(us.equals("")||ps.equals("")||rep.equals("")){
                    Toast.makeText(profileform.this,"Fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(ps.equals(rep)){
                        Boolean result = DB.checkusername(ps);
                        if(result==false){
                           Boolean regresult=  DB.insertData(us,ps);
                           if(regresult==true){
                               Toast.makeText(profileform.this,"Profile is created successfully",Toast.LENGTH_SHORT).show();
                           }
                           else{
                               Toast.makeText(profileform.this,"Profile creation is failed",Toast.LENGTH_SHORT).show();
                           }
                        }
                        else{
                            Toast.makeText(profileform.this,"User already Exists.\n Please Login",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(profileform.this,"Password not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}