package com.example.mylobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table User(Username text,email text,password text)";
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String Username,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("Username",Username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("Users",null,cv);
        db.close();

    }

    public int login(String Username,String password){
        int result=0;
        String str[] = new String[2];
        str[0] = Username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from Users where Username=? and password=?",str);
        if (c.moveToFirst()){
            result=1;
        }
        return result;
    }
}
