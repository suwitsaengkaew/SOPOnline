package com.example.suwitsaengkaew.soponline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by suwitsaengkaew on 7/12/2017 AD.
 */

public class DocumentListTABLE {

    private MySQLiteOpenHelper ObjMySQLiteOpenHelper;
    private SQLiteDatabase writeSQL, readSQL;

    private static final String DOCUMENTLISTTABLE = "documentlistTABLE";
    private static final String COLUMN_CODE = "org_code";
    private static final String COLUMN_NAME = "org_name";
    private static final String COLUMN_ID = "doc_id";
    private static final String COLUMN_NUMBER = "doc_number";
    private static final String COLUMN_SUJECT = "doc_subject";
    private static final String COLUMN_STATUS = "issue_status";
    private static final String COLUMN_TYPE = "doc_type";

    public DocumentListTABLE(Context context) {

        ObjMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQL = ObjMySQLiteOpenHelper.getWritableDatabase();
        readSQL = ObjMySQLiteOpenHelper.getReadableDatabase();

    }

    public long insertValue (String org_code, String org_name, String doc_id, String doc_number, String doc_subject, String issue_status, String doc_type) {

        ContentValues ObjContentValues = new ContentValues();
        ObjContentValues.put(COLUMN_CODE, org_code);
        ObjContentValues.put(COLUMN_NAME, org_name);
        ObjContentValues.put(COLUMN_ID, doc_id);
        ObjContentValues.put(COLUMN_NUMBER, doc_number);
        ObjContentValues.put(COLUMN_SUJECT, doc_subject);
        ObjContentValues.put(COLUMN_STATUS, issue_status);
        ObjContentValues.put(COLUMN_TYPE, doc_type);

        return writeSQL.insert(DOCUMENTLISTTABLE, null, ObjContentValues);
    }

    public String[] searchDocumentList(String orgCode) {

        try {

            String strDocumentList[] = null;

            Cursor ObjCursor = readSQL.query(DOCUMENTLISTTABLE,
                    new String[] { COLUMN_NUMBER, COLUMN_SUJECT, COLUMN_TYPE }, COLUMN_CODE + "=?",
                    new String[] {String.valueOf(orgCode)}, null, null, null, null);

            if (ObjCursor != null) {
                if (ObjCursor.moveToFirst()) {

                }
            }

        } catch (Exception e) {

            return null;

        }
    }
}
