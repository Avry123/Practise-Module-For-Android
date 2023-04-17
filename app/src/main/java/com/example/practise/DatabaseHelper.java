package com.example.practise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  String db_name = "trial.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context,db_name, null,1);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS students (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT);");


    }

    public boolean insertData(Integer id1 , String name, String email, String password) {
           SQLiteDatabase insertObject = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id1);
        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);
        long e = insertObject.insert("students",null,cv);
        if (e != -1) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor showRecords() {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor res = db.rawQuery("SELECT * FROM students", null);
    return res;

    }

    //Show specific record.
    public Cursor showSpecificRecord(int id1) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM students WHERE id = " + id1, null);
        return res;
    }

    //Update a record
    public boolean updateRecord(Integer id1, String name1, String email1, String password1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id1);
        cv.put("name", name1);
        cv.put("email", email1);
        cv.put("password", password1);
        long e = db.update("students",cv,"id=?", new String[] {String.valueOf(id1)});
        if (e != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteARecord(Integer id1) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Delete a record.
        long e = db.delete("students","id=?", new String[] {String.valueOf(id1)});
        if (e != -1) {
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
