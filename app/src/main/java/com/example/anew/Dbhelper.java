package com.example.anew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Dbhelper extends SQLiteOpenHelper {
    String dbname = "mydb.db";
    public Dbhelper(@Nullable Context context) {
        super(context, "mydb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table Crudtable(Id Integer primary key autoincrement , Name text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop Table if Exists Crudtable");
    }
    public boolean Datainsert(String Name){

        // for all function to make a object;

        SQLiteDatabase db = this.getWritableDatabase();

        // for insert value in right table;

        ContentValues insert = new ContentValues();
        insert.put("Name" , Name);

        // for insert data in table;

        long result = db.insert("Crudtable" , null , insert);

        // for result confirm;

        if (result > 0){
            return true;
        }
        else {
            return false;
        }
    }



    public List<String> fetch2(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor find = db.rawQuery("Select * from Crudtable" , null);
        List<String> tablerecord = new ArrayList<>();
        while (find.moveToNext()){
            tablerecord.add(find.getString(1));
        }
        return tablerecord;
    }


}
