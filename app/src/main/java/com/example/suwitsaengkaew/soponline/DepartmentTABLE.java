package com.example.suwitsaengkaew.soponline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by suwitsaengkaew on 7/12/2017 AD.
 */

public class DepartmentTABLE {

    private MySQLiteOpenHelper ObjMySQLiteOpenHelper;
    private SQLiteDatabase writeSQL, readSQL;

    public static final String DEPARTMENTTABLE = "departmentTABLE";
    public static final String COLUMN_CODE = "org_code";
    public static final String COLUMN_NAME = "org_name";

    public DepartmentTABLE (Context context) {

        ObjMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQL = ObjMySQLiteOpenHelper.getWritableDatabase();
        readSQL = ObjMySQLiteOpenHelper.getReadableDatabase();

    } // Constructor

    public long insertValue(String code, String name) {

        ContentValues ObjContentValues = new ContentValues();
        ObjContentValues.put(COLUMN_CODE, code);
        ObjContentValues.put(COLUMN_NAME, name);

        return writeSQL.insert(DEPARTMENTTABLE, null, ObjContentValues);
    }

    public String[] orgCode () {

        String strListCode[] = null;

        Cursor ObjCursor = readSQL.query(DEPARTMENTTABLE, new String[] { COLUMN_CODE }, null, null, null, null, null);
        ObjCursor.moveToFirst();

        strListCode = new String[ObjCursor.getCount()];

        for (int i = 0; i < ObjCursor.getCount(); i++) {
            strListCode[i] = ObjCursor.getString(ObjCursor.getColumnIndex(COLUMN_CODE));
            ObjCursor.moveToNext();
            Log.d("SOPOnline", strListCode[i].toString());
        }

        ObjCursor.close();

        return strListCode;
    }

    public String[] orgName () {

        String strListdName[] = null;

        Cursor ObjCursor = readSQL.query(DEPARTMENTTABLE, new String[] { COLUMN_NAME }, null, null, null, null, null);
        ObjCursor.moveToFirst();

        strListdName = new String[ObjCursor.getCount()];

        for (int i = 0; i < ObjCursor.getCount(); i++) {
            strListdName[i] = ObjCursor.getString(ObjCursor.getColumnIndex(COLUMN_NAME));
            ObjCursor.moveToNext();
            Log.d("SOPOnline", strListdName[i].toString());
        }

        ObjCursor.close();

        return strListdName;

    }
}
