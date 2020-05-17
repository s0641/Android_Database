package com.app.sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //Table field
    public static final String KEY_ROWID = "_id";
    public static final String KEY_USERNAME= "username";
    public static final String KEY_PASSWORD = "password";


    private static final String DATABASE_NAME = "usersdb";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_CREATE =
            "create table users (_id integer primary key autoincrement, "
                    + "username text not null, "
                    + "password text not null);";

    private Context context = null;
    private SQLiteDatabase db;

    //Tools->android->Android Device Monitor->file Explorer
    //To view database goto terminal window
    //C:\Users\dzonePC\AppData\Local\Android\sdk\platform-tools>
    //adb root
    //goto data/data/packagename/database

    MyDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w("shubham", "Upgrading database from version " + oldVersion
                + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public long AddUser(String username, String password)
    {
        db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_PASSWORD, password);
        return db.insert(DATABASE_TABLE, null, initialValues);

    }

    public Boolean Login(String username, String password) throws SQLException
    {
        db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE username=? AND password=?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList Getdata()
    {
        ArrayList<String > results  = new ArrayList<>();
        try {

            db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM users",null);

            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        results.add(c.getString( c.getColumnIndex("username")));
                    }while (c.moveToNext());
                }
            }
        }
        catch (Exception ex)
        {

        }
        return results;
    }



    public int update(long _id, String name, String pass) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, name);
        contentValues.put(KEY_PASSWORD, pass);
        int i = db.update(DATABASE_TABLE, contentValues, KEY_ROWID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_ROWID + "=" + _id, null);
    }
}

