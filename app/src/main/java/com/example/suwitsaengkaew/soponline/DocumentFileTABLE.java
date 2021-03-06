package com.example.suwitsaengkaew.soponline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by suwitsaengkaew on 7/12/2017 AD.
 */

public class DocumentFileTABLE {

    private MySQLiteOpenHelper ObjMySQLiteOpenHelper;
    private SQLiteDatabase writeSQL, readSQL;

    private static final String DOCUMENTFILETABLE = "documentfileTABLE";
    private static final String COLUMN_DOCID = "doc_id";
    private static final String COLUMN_FILENAME = "file_name";
    private static final String COLUMN_FILEPATH = "file_path";
    private static final String COLUMN_FILEURL = "file_url";

    public DocumentFileTABLE (Context context) {

        ObjMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQL = ObjMySQLiteOpenHelper.getWritableDatabase();
        readSQL = ObjMySQLiteOpenHelper.getReadableDatabase();

    }

    public long insertValue (String doc_id, String file_name, String file_path, String file_url) {

        ContentValues ObjContentValues = new ContentValues();
        ObjContentValues.put(COLUMN_DOCID, doc_id);
        ObjContentValues.put(COLUMN_FILENAME, file_name);
        ObjContentValues.put(COLUMN_FILEPATH, file_path);
        ObjContentValues.put(COLUMN_FILEURL, file_url);

        return writeSQL.insert(DOCUMENTFILETABLE, null,ObjContentValues);
    }

    public String[] searchDocumentFile (String doc_id) {

        String fileName[] = null;

        try {

            Cursor ObjCursor = readSQL.rawQuery("SELECT file_url FROM documentfileTABLE WHERE doc_id = '" +
                    doc_id.toString().trim() + "'", null);
            ObjCursor.moveToFirst();

            fileName = new String[ObjCursor.getCount()];

            for (int i = 0; i < ObjCursor.getCount(); i++) {
                fileName[i] = ObjCursor.getString(ObjCursor.getColumnIndex("file_url"));
                ObjCursor.moveToNext();
                // Log.d("SOPOnline", "File name ==> " + fileName[i]);
            }

            ObjCursor.close();


        } catch (Exception e) {
            Log.d("SOPOnline", "File name get error " + e.toString());
        }

        return fileName;

    }
}
