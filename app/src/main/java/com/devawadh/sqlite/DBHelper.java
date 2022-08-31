package com.devawadh.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create Table Userdetail(name TEXT primary key,email TEXT,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop Table if exists Userdetail");
    }
    public  boolean insertdata(String name,String email,String phone){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues vv=new  ContentValues();
        vv.put("name",name);
        vv.put("email",email);
        vv.put("phone",phone);
        long result=db.insert("Userdetail",null,vv);
        if(result==-1){

            return  false;
        }else {

            return  true;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Userdetail",null);
         return cursor;
    }
}
