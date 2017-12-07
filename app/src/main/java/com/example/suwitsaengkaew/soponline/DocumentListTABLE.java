package com.example.suwitsaengkaew.soponline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by suwitsaengkaew on 7/12/2017 AD.
 */

public class DocumentListTABLE {

    private MySQLiteOpenHelper ObjMySQLiteOpenHelper;
    private SQLiteDatabase writeSQL, readSQL;

    public DocumentListTABLE(Context context) {

        ObjMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQL = ObjMySQLiteOpenHelper.getWritableDatabase();
        readSQL = ObjMySQLiteOpenHelper.getReadableDatabase();

    }
}
