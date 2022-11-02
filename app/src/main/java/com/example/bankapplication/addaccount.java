package com.example.bankapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addaccount extends AppCompatActivity {
    Button accbtn, accbtn2;
    EditText no, aname, code, bname, mobile, type, balance;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaccount);
        getSupportActionBar().setTitle("Add Account");

        no =(EditText) findViewById(R.id.no);
        aname =(EditText) findViewById(R.id.aname);
        code =(EditText) findViewById(R.id.code);
        bname = (EditText) findViewById(R.id.bname);
        mobile = (EditText) findViewById(R.id.mobile);
        type = (EditText) findViewById(R.id.type);
        balance = (EditText) findViewById(R.id.balance);

        accbtn = (Button) findViewById(R.id.accbtn);
        accbtn2 =(Button) findViewById(R.id.accbtn2);

        DB = new DBHelper(this);

        accbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String No = no.getText().toString();
                String name = aname.getText().toString();
                String Code = code.getText().toString();
                String Bname = bname.getText().toString();
                String Mobile = mobile.getText().toString();
                String Type = type.getText().toString();
                String Balance = balance.getText().toString();

                Boolean checkinsertdata = DB.insertaccount(No,name,Code,Bname,Mobile,Type,Balance);
                if(checkinsertdata==true){
                    Toast.makeText(addaccount.this,"Account added Successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(addaccount.this,"Adding account failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        accbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(addaccount.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Account Number:"+res.getString(0)+"\n");
                    buffer.append("Account Name:"+res.getString(1)+"\n");
                    buffer.append("IFSC:"+res.getString(2)+"\n");
                    buffer.append("Bank Name:"+res.getString(3)+"\n");
                    buffer.append("Mobile Number:"+res.getString(4)+"\n");
                    buffer.append("Account Type:"+res.getString(5)+"\n");
                    buffer.append("Available Balance:"+res.getString(6)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(addaccount.this);
                builder.setCancelable(true);
                builder.setTitle("Account Details");
                builder.setMessage(buffer.toString());
                builder.show();
        }
        });
    }
}
