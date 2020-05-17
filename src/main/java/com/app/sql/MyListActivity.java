package com.app.sql;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MyListActivity extends AppCompatActivity {

    GridView myGrid;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        myGrid=(GridView) findViewById(R.id.gridView);

        MyDatabaseHelper dbUser = new MyDatabaseHelper(MyListActivity.this);


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, dbUser.Getdata());

        myGrid.setAdapter(adapter);

    }
}