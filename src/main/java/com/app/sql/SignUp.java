package com.app.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {


    EditText e1,e2;
    Button b3;

    String uname, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);

        b3=(Button)findViewById(R.id.button6);






        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
              /*
                DBUserAdapter dbUser = new DBUserAdapter(SignUp.this);
                dbUser.open();
                uname = e1.getText().toString();
                pass = e2.getText().toString();
                long k= dbUser.AddUser(uname,pass);
            */

                MyDatabaseHelper dbUser = new MyDatabaseHelper(SignUp.this);

                uname = e1.getText().toString();
                pass = e2.getText().toString();
                long k= dbUser.AddUser(uname,pass);
                Toast.makeText(SignUp.this, "Record Inserted "+k+ " of "+ uname + ", "+ pass, Toast.LENGTH_LONG).show();
            }
        });




    }



}
