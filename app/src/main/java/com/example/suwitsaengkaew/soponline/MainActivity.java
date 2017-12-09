package com.example.suwitsaengkaew.soponline;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private DepartmentTABLE ObjDepartmentTable;
    private DocumentListTABLE ObjDocumentListTable;
    private DocumentFileTABLE ObjDocumentFileTable;


//    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }

//        if (Build.VERSION.SDK_INT  < Build.VERSION_CODES.FROYO) {
//            System.setProperty("http.keepAlive", "false");
//        }

        SyncDatabase();
        bindWidget();
//        pdfView = (PDFView) findViewById(R.id.pdfView);
//        pdfView.fromAsset("demo.pdf").load();
    }

    private void bindWidget() {
        Button btnStart = (Button) findViewById(R.id.btnStart);
    }


    private void SyncDatabase() {



        ObjDepartmentTable = new DepartmentTABLE(this);
        ObjDocumentListTable = new DocumentListTABLE(this);
        ObjDocumentFileTable = new DocumentFileTABLE(this);

        SQLiteDatabase objSQLite = openOrCreateDatabase("SopOnline.db", MODE_PRIVATE, null);
        objSQLite.delete("departmentTABLE", null,null);
        objSQLite.delete("documentlistTABLE", null,null);
        objSQLite.delete("documentfileTABLE", null, null);
        Log.d("SOPOnline", "Erase Table Complete");

        // Sync DepartmentTable
        String strDepartmentJSON = "";
        InputStream departmentStreamfromWepAPI = null;

        try {

            URL url = new URL("http://info.ytmt.co.th/dmsapi/documentsection");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {

                departmentStreamfromWepAPI = new BufferedInputStream(urlConnection.getInputStream());
                Log.d("SOPOnline", "Complete get data from WebAPI ==> " + departmentStreamfromWepAPI.toString());

                try {

                    ByteArrayOutputStream ObjBo = new ByteArrayOutputStream();
                    int i = departmentStreamfromWepAPI.read();

                    while (i != -1) {
                        ObjBo.write(i);
                        i = departmentStreamfromWepAPI.read();
                    }

                    strDepartmentJSON = ObjBo.toString();

                    ObjBo.close();

                } catch (IOException e) {

                    Log.d("SOPOnline", "IOExecption error ==> " + e.toString());

                }

                try {

                    final JSONArray ObjJSONArray = new JSONArray(strDepartmentJSON);
                    for (int i = 0; i < ObjJSONArray.length(); i++) {

                        JSONObject ObjJSONObject = ObjJSONArray.getJSONObject(i);
                        String strOrg_Code = ObjJSONObject.getString("org_code");
                        String strOrg_Name = ObjJSONObject.getString("org_name");

                        long insertValues = ObjDepartmentTable.insertValue(strOrg_Code, strOrg_Name);

                    }

                    Log.d("SOPOnline", "Complete insert values to Department TABLE");

                } catch (Exception e) {

                    Log.d("SOPOnline", "Error cannot write to SQLite ==> " + e.toString());

                }

            } catch (Exception e) {

                Log.d("SOPOnline", "Error Http connection not complete ==> " + e.toString());

            } finally {

                urlConnection.disconnect();

            }

        } catch (Exception e) {

            Log.d("SOPOnline","Error get stream from Department Table ==> " + e.toString());

        }

        // Sync DepartmentTable

        // Sync DocumentListTable

        String strDocumentListJSON = "";
        InputStream DocumentListStreamfromWepAPI = null;

        try {

            URL url = new URL("http://info.ytmt.co.th/dmsapi/documentlist");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {

                DocumentListStreamfromWepAPI = new BufferedInputStream(urlConnection.getInputStream());
                Log.d("SOPOnline", "Complete get data from WebAPI ==> " + DocumentListStreamfromWepAPI.toString());

                try {

                    ByteArrayOutputStream ObjBo = new ByteArrayOutputStream();
                    int i = DocumentListStreamfromWepAPI.read();

                    while (i != -1) {
                        ObjBo.write(i);
                        i = DocumentListStreamfromWepAPI.read();
                    }

                    strDocumentListJSON = ObjBo.toString();

                    ObjBo.close();

                } catch (IOException e) {

                    Log.d("SOPOnline", "IOExecption error ==> " + e.toString());

                }

                try {

                    final JSONArray ObjJSONArray = new JSONArray(strDocumentListJSON);
                    for (int i = 0; i < ObjJSONArray.length(); i++) {

                        JSONObject ObjJSONObject = ObjJSONArray.getJSONObject(i);
                        String strOrg_Code = ObjJSONObject.getString("org_code");
                        String strOrg_Name = ObjJSONObject.getString("org_name");
                        String strDoc_Id = ObjJSONObject.getString("doc_id");
                        String strDoc_Number = ObjJSONObject.getString("doc_number");
                        String strDoc_Suject = ObjJSONObject.getString("doc_subject");
                        String strIssue_Status = ObjJSONObject.getString("issue_status");
                        String strDoc_Type = ObjJSONObject.getString("doc_type");

                        long insertValues = ObjDocumentListTable.insertValue(strOrg_Code, strOrg_Name, strDoc_Id, strDoc_Number, strDoc_Suject, strIssue_Status, strDoc_Type);

                    }

                    Log.d("SOPOnline", "Complete insert values to DocumentList TABLE");

                } catch (Exception e) {

                    Log.d("SOPOnline", "Error cannot write to SQLite ==> " + e.toString());

                }

            } catch (Exception e) {

                Log.d("SOPOnline", "Error Http connection not complete ==> " + e.toString());

            } finally {

                urlConnection.disconnect();

            }

        } catch (Exception e) {

            Log.d("SOPOnline","Error get stream from DocumentList Table ==> " + e.toString());

        }

        // Sync DocumentListTable

        // Sync DocumentFileTable

        String strDocumentFileJSON = "";
        InputStream documentFileStreamfromWepAPI = null;

        try {

            URL url = new URL("http://info.ytmt.co.th/dmsapi/documentfile");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {

                documentFileStreamfromWepAPI = new BufferedInputStream(urlConnection.getInputStream());
                Log.d("SOPOnline", "Complete get data from WebAPI ==> " + documentFileStreamfromWepAPI.toString());

                try {

                    ByteArrayOutputStream ObjBo = new ByteArrayOutputStream();
                    int i = documentFileStreamfromWepAPI.read();

                    while (i != -1) {
                        ObjBo.write(i);
                        i = documentFileStreamfromWepAPI.read();
                    }

                    strDocumentFileJSON = ObjBo.toString();

                    ObjBo.close();

                } catch (IOException e) {

                    Log.d("SOPOnline", "IOExecption error ==> " + e.toString());

                }

                try {

                    final JSONArray ObjJSONArray = new JSONArray(strDocumentFileJSON);
                    for (int i = 0; i < ObjJSONArray.length(); i++) {

                        JSONObject ObjJSONObject = ObjJSONArray.getJSONObject(i);
                        String strDoc_Id = ObjJSONObject.getString("doc_id");
                        String strFile_Name = ObjJSONObject.getString("file_name");
                        String strFile_Path = ObjJSONObject.getString("file_path");
                        String strFile_Url = ObjJSONObject.getString("file_url");

                        long insertValues = ObjDocumentFileTable.insertValue(strDoc_Id, strFile_Name, strFile_Path, strFile_Url);

                    }

                    Log.d("SOPOnline", "Complete insert values to DocumentFile TABLE");

                } catch (Exception e) {

                    Log.d("SOPOnline", "Error cannot write to SQLite ==> " + e.toString());

                }

            } catch (Exception e) {

                Log.d("SOPOnline", "Error Http connection not complete ==> " + e.toString());

            } finally {

                urlConnection.disconnect();

            }

        } catch (Exception e) {

            Log.d("SOPOnline","Error get stream from DocumentFile Table ==> " + e.toString());

        }

        // Sync DocumentFileTable
    }

    public void btnStartOnClick(View view) {


        Intent ObjIntentDepartmentListActivity = new Intent(MainActivity.this, DepartmentActivity.class);
        startActivity(ObjIntentDepartmentListActivity);
//        AlertDialog onClickAlert = new AlertDialog();
//        onClickAlert.errorDialog(this,"Test","Test");
    }
}
