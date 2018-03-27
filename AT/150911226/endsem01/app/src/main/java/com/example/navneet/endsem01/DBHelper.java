package com.example.navneet.endsem01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Navneet on 24-03-2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBname = "end.db";
    private static final int version = 1;
    public static String tID="tid";
    public static String tName="tname";
    public static String tData="tdata";
    private SQLiteDatabase dbs;

    public DBHelper(Context context){super(context, DBname, null, version);}

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "Create table data("+tID+"INTEGER Primary key AUTOINCREMENT,"+tName+"TEXT ,"+tData+" TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertData(String name, String data){
        dbs=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tName, name);
        values.put(tData, data);
        long r = dbs.insert("data", null, values);
        dbs.close();
        return r;
    }

    public long delete(int tid){
        String where = tID+"="+tid;
        return dbs.delete("data", where, null);
    }
    public long update(int tid, String name, String data){
        ContentValues values = new ContentValues();
        values.put(tName, name);
        values.put(tData, data);
        String where = tID+"="+tid;
        return dbs.update("data",values, where, null );
    }
    public Cursor getData(int tid){
        String where = tID+"="+tid;
        dbs = getWritableDatabase();
        return dbs.query("data",null, where, null, null, null, null);

    }
    public Cursor query(String query){
        dbs = getWritableDatabase();
        return dbs.rawQuery(query, new String[]{});
    }
    public void execSQL(String query){
        dbs=getWritableDatabase();
        dbs.execSQL(query);
    }

}
