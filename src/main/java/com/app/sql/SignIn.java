package com.app.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignIn extends AppCompatActivity {

    SQLiteDatabase db;

    EditText e1,e2;
    String uname,password;
    Button b1,b2,b3;
    TextView T6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);



        b1=(Button)findViewById(R.id.button3);
        b2=(Button)findViewById(R.id.button4);
        b3=(Button)findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                uname=e1.getText().toString();
                password=e2.getText().toString();

              /*  DBUserAdapter dbUser = new DBUserAdapter(SignIn.this);
                dbUser.open();  */

                MyDatabaseHelper dbUser = new MyDatabaseHelper(SignIn.this);

                if(dbUser.Login(uname,password))
                {
                    Toast.makeText(SignIn.this, "login success ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SignIn.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent x=new Intent(SignIn.this,SignUp.class);
                        startActivity(x);
                    }
                });

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent x=new Intent(SignIn.this,MyListActivity.class);
                        startActivity(x);
                    }
                });
            }
        });

    }
}
