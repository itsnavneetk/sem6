package com.example.divyanshu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.Cursor;

/**
 * Created by divyanshu on 13/3/18.
 */

public class MyDBHelper extends SQLiteOpenHelper {


    private static final String DBNAME = "eval.db";
    private static final Integer VERSION = 1;
    public static String TABLE_NAME = "tblemp";
    public static String ID = "empid";
    public static String NAME = "name";
    public static String ADDRESS = "addr";
    private SQLiteDatabase dbs;
    public MyDBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry_table = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT NOT NULL," + ADDRESS + " TEXT NOT NULL);";

        db.execSQL(qry_table);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(int id, String name, String address) {
        dbs = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(NAME, name);
        values.put(ADDRESS, address);
        return dbs.insert(TABLE_NAME, null, values);
    }

    public long delete(int id) {
        String where = ID + " = " + id;

        return dbs.delete(TABLE_NAME, where, null);
    }

    public long update(int id, String name, String address) {
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(ADDRESS, address);
        String where = ID + " = " + id;
        return dbs.update(TABLE_NAME, values, where, null);
    }

    public Cursor getAllEmployee() {
        //String query = "SELECT * FROM " + TABLE_NAME;
        //return dbs.rawQuery(query, null);
        return dbs.query(TABLE_NAME,null,null,null,null,null,NAME);
    }


}
