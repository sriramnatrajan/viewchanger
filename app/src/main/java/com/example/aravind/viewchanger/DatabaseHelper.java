package com.example.aravind.viewchanger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public  static final String DATABASE_NAME="people.db";
    public  static final String TABLE_NAME="people_table";
    public  static final String COL1="ID";
    public  static final String COL2="Name";
    public  static final String COL3="Password";
    public  static final String COL4="Place";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable=" CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME TEXT NOT NULL,PASSWORD TEXT NOT NULL,PLACE TEXT NOT NULL)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public  boolean addData(String name,String password,String place)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,password);
        contentValues.put(COL4,place);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
        {
            return false;
        }
        else
        {
            return  true;
        }


    }
    public  boolean chkname(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery( "SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL2 + " =? ",new String[]{name});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
    public Boolean chkpassword(String name,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL2 + " =? AND " + DatabaseHelper.COL3 + "=?",new String[]{name,password});

                        if(cursor.getCount()>0)
                    return true;
                else
                    return false;

                }



                public  Cursor getListContents()
                {
                    SQLiteDatabase db=this.getWritableDatabase();
                    Cursor data=db.rawQuery("SELECT * FROM " +TABLE_NAME,null);
                    return data;
                }

    /*public  Cursor showNext(String i,String j)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM " +TABLE_NAME +" LIMIT"+ i,j,null);
        return data;
    }*/
    public Cursor showData()
    {
        String[] column={COL1,COL2,COL3,COL4};
        //return db.query(TABLE_NAME,column,null,null,null,null);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return data;
    }


    public  boolean updateData(String id,String name,String password,String place)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,password);
        contentValues.put(COL4,place);
        db.update(TABLE_NAME, contentValues,"ID = ?" ,new String[] {id});
        return true;

    }
    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID =?",new String[]{id});

    }

}
