package com.example.suwitsaengkaew.soponline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    private static final String COLUMN_SUBJECT = "doc_subject";
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
        ObjContentValues.put(COLUMN_SUBJECT, doc_subject);
        ObjContentValues.put(COLUMN_STATUS, issue_status);
        ObjContentValues.put(COLUMN_TYPE, doc_type);

        return writeSQL.insert(DOCUMENTLISTTABLE, null, ObjContentValues);
    }

    public String[][] searchDocumentList2D(String orgCode) {

        try {

            String strDocumentList[][] = null;
            int cPos;
            Cursor ObjCursor = readSQL.query(DOCUMENTLISTTABLE,
                    new String[] { COLUMN_NUMBER, COLUMN_SUBJECT }, COLUMN_CODE + "=?",
                    new String[] {String.valueOf(orgCode)}, null, null, null, null);

            if (ObjCursor.getCount() > 0) {

                strDocumentList = new String[ObjCursor.getCount()][2];
                ObjCursor.moveToFirst();
                do {
                    cPos = ObjCursor.getPosition();
                    strDocumentList[cPos][0] = ObjCursor.getString(ObjCursor.getColumnIndex(COLUMN_NUMBER));
                    strDocumentList[cPos][1] = ObjCursor.getString(ObjCursor.getColumnIndex(COLUMN_SUBJECT));

                } while (!ObjCursor.isAfterLast());

            }

            ObjCursor.close();
            return strDocumentList;

        } catch (Exception e) {

            return null;

        }
    }

    public String[][] searchDocumentList1D(String orgCode) {

        try {
            String[][] strDocumentList = null;
            int cPos;
            Cursor ObjCursor = readSQL.rawQuery("SELECT doc_number, doc_subject FROM documentlistTABLE WHERE org_code = '"
                    + orgCode.toString().trim() + "' ORDER BY doc_number", null);

            if (ObjCursor.getCount() > 0) {

                Log.d("SOPOnline", "Get Count ==> " + ObjCursor.getCount());

                strDocumentList = new String[ObjCursor.getCount()][2];
                ObjCursor.moveToFirst();
                do {
                    cPos = ObjCursor.getPosition();
                    strDocumentList[cPos][0] = ObjCursor.getString(ObjCursor.getColumnIndex("doc_number"));
                    Log.d("SOPOnline", "doc_number ==> " + cPos + "-" + strDocumentList[cPos][0]);
                    strDocumentList[cPos][1] = ObjCursor.getString(ObjCursor.getColumnIndex("doc_subject"));
                    Log.d("SOPOnline", "doc_subject ==> " + strDocumentList[cPos][1]);
                    ObjCursor.moveToNext();
                } while (!ObjCursor.isAfterLast());

            }

            ObjCursor.close();
            return strDocumentList;

        } catch (Exception e) {

            Log.d("SOPOnline", " ==> " + e.toString());
            return null;

        }
    }
}
