package com.example.tsaw.javaFile;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public static  final  String Database_Name="TSAW_Login";
    public static  final String Table_Name="User_Details";
    public static  final String Token="";
    public static  final String User_ID="";
    public static  final String Password="";
    public static  final Integer ID=0;



    public Dbhelper(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+ Table_Name +"(ID Integer Primary Key Autoincrement,User_ID String,Password String,Token String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("Drop table if Exists "+Table_Name);
    onCreate(db);
    }

    public boolean InsertData(String User_ID,String Password,String Token)
    {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("User_ID",User_ID);
        values.put("Password",Password);
        values.put("Token",Token);

        long result  = DB.insert(Table_Name,null,values);
        if (result==-1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor Getdata()
    {
    SQLiteDatabase DB = this.getWritableDatabase();
    Cursor cursor=DB.rawQuery("Select * from "+Table_Name,null);
    return cursor;
    }

    public boolean Updatedata(String User_Id,String Password,String Token,String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("User_ID",User_Id);
        c.put("Password",Password);
        c.put("Token",Token);
        DB.update(Table_Name,c,"ID = ?",new String[] {id});
return true;
    }
    public boolean DeleteData(String JWT_Token)
    {
        SQLiteDatabase Db = this.getReadableDatabase();
        Db.delete(Table_Name,JWT_Token,null);
        return true;
    }
}
