package com.example.suwitsaengkaew.soponline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suwitsaengkaew on 7/12/2017 AD.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SopOnline.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DEPARTMENT_TABLE = "create table departmentTABLE (_id integer primary key, org_code text, org_name text);";
    private static final String DOCUMENTLIST_TABLE = "create table documentlistTABLE (_id integer primary key, org_code text, org_name text, doc_id text, doc_number text, doc_subject text, issue_status text, doc_type text);";
    private static final String DOCUMENTFILE_TABLE = "create table documentfileTABLE (_id integer primary key, doc_id text, file_name text, file_path text, file_url text);";

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DEPARTMENT_TABLE);
        db.execSQL(DOCUMENTLIST_TABLE);
        db.execSQL(DOCUMENTFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
