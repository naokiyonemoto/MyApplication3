package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TABLE_NAME = "person_data";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SUB_NAME = "sub_name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_COMPANY = "company";

    //PersonDataを保持しているDB
    private static final String DATABASE_NAME = "pesondata.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+ TABLE_NAME + " (");
        sb.append(COLUMN_ID + " INTEGER PRIMARY KEY,");
        sb.append(COLUMN_NAME + " TEXT, ");
        sb.append(COLUMN_SUB_NAME + " TEXT, ");
        sb.append(COLUMN_PHONE_NUMBER + " TEXT, ");
        sb.append(COLUMN_EMAIL + " TEXT, ");
        sb.append(COLUMN_COMPANY + " TEXT");
        sb.append(");");
        String sql = sb.toString();
        db.execSQL(sql);

//        //サンプルデータ
//        ContentValues values = new ContentValues();
//        values.put("name", "米本1");
//        values.put("sub_name", "よねもと1");
//        values.put("phone_number", "00011112222");
//        values.put("email", "test1@mail");
//        values.put("company", "nus");
//        db.insert("person_data", null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
