package com.example.student.ex1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBName="products.db";
    private static final int version=1;
    public static String prodId="prod_id";
    public static String prodName="prod_name";
    public static String prodPrice="prod_price";
    private SQLiteDatabase dbs;

    public DBHelper(Context context)
    {
        super(context,DBName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query="CREATE TABLE products("+prodId+" TEXT PRIMARY KEY, "+prodName+" TEXT, "+prodPrice+" INTEGER);";
        db.execSQL(query);

        query="CREATE TABLE bill("+prodId+" TEXT PRIMARY KEY, qty INTEGER);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public long insertProd(String pId, String pName, int pPrice)
    {
        dbs=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(prodId,pId);
        values.put(prodName,pName);
        values.put(prodPrice,pPrice);
        long r=dbs.insert("products",null,values);
        dbs.close();
        return r;
    }

    public long insertBill(String pId)
    {
        dbs=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(prodId,pId);
        values.put("qty",1);
        long r=dbs.insert("bill",null,values);
        dbs.close();
        return r;
    }

    public long delete(String tableName, String pId)
    {
        String where=prodId+"="+pId;
        return  dbs.delete(tableName,where,null);
    }

    public long updateProd(String pId, String pName, int pPrice)
    {
        ContentValues values=new ContentValues();
        values.put(prodName,pName);
        values.put(prodPrice,pPrice);
        String where=prodId+"='"+pId+"'";
        return dbs.update("products",values,where,null);
    }

    public long updateBill(String pId, int qty)
    {
        ContentValues values=new ContentValues();
        values.put("qty",qty);
        String where=prodId+"='"+pId+"'";
        return dbs.update("bill",values,where,null);
    }

    public Cursor getProd(String pId)
    {
        String where=prodId+"='"+pId+"'";
        dbs=getWritableDatabase();
        return dbs.query("products",null,where,null,null,null,null);
    }

    public Cursor query(String query)
    {
        dbs=getWritableDatabase();
        return dbs.rawQuery(query, new String[]{});
    }

    public void execSQL(String query)
    {
        dbs=getWritableDatabase();
        dbs.execSQL(query);
    }
}
