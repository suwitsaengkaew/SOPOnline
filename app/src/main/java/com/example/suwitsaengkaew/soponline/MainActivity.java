package com.example.suwitsaengkaew.soponline;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private DepartmentTABLE ObjDepartmentTable;
    private DocumentListTABLE ObjDocumentListTable;
    private DocumentFileTABLE ObjDocumentFileTable;

    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SyncDatabase();


        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }

        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("demo.pdf").load();
    }

    private void SyncDatabase() {

        ObjDepartmentTable = new DepartmentTABLE(this);
        ObjDocumentListTable = new DocumentListTABLE(this);
        ObjDocumentFileTable = new DocumentFileTABLE(this);

        // Sync DepartmentTable
        String strDepartmentJSON = "";
        InputStream departmentStreamfromWepAPI = null;

        try {

            URL url = new URL("http://info.ytmt.co.th/dmsapi/documentsection");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {

                departmentStreamfromWepAPI = new BufferedInputStream(urlConnection.getInputStream());
                Log.d("SOPOnline", "Complete get data from WebAPI ==> " + departmentStreamfromWepAPI.toString())

            } catch (Exception e) {

              Log.d("SOPOnline", "Error Http connection not complete ==> " + e.toString());

            } finally {

              urlConnection.disconnect();

            }

        } catch (Exception e) {

            Log.d("SOPOnline","Error get stream from Department Table ==> " + e.toString());

        }

        try {

        } catch (Exception e) {
            
        }
        // Sync DepartmentTable
    }
}
