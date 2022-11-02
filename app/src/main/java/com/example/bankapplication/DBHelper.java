package com.example.bankapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static  final String DBNAME = "Login.db";
    private static  final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {

        super(context,DBNAME, null,DATABASE_VERSION);
    }

    @Override
    public void  onCreate(SQLiteDatabase MyDB){
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        String qry = "create table tbl_bank (ano integer primary key, name text ,code text, bname text, mobile text, type text, balance text)";
        MyDB.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1){
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("DROP TABLE IF EXISTS tbl_bank");
        onCreate(MyDB);
    }
    public  Boolean insertaccount(String ano, String name, String code, String bname, String mobile, String type, String balance){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ano",ano);
        cv.put("name",name);
        cv.put("code",code);
        cv.put("bname",bname);
        cv.put("mobile",mobile);
        cv.put("type",type);
        cv.put("balance",balance);
        long result = MyDB.insert("tbl_bank",null,cv);
        if(result == -1){
            return  false;
        }
        else {
            return  true;
        }
    }

    public  Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from tbl_bank";
        Cursor cursor = db.rawQuery(qry,null);
        return  cursor;
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else
            return  true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

