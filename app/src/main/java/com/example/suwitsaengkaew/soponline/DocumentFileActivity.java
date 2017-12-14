package com.example.suwitsaengkaew.soponline;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class DocumentFileActivity extends AppCompatActivity {

    private PDFView pdfView;
    //private PermissionCheck permissionCheck;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentfile_activity);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }

        clearESmartFile();
        // Log.d("SOPOnline", "Get Intent ==> " + getIntent().getExtras().getString("url") );
        String Url = getIntent().getExtras().getString("url");



        //Uri uri = Uri.parse(Url);
        //Log.d("SOPOnline", "Uri ==> " + uri.toString());
        //pdfView = (PDFView) findViewById(R.id.pdfWebView);
        //pdfView.fromUri(uri).load();
        //pdfView.fromAsset("demo.pdf").load();
        if  (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        }
        else {
            if (doInBackground(Url) == true) {

                Log.d("SOPOnline", "Check file");


                new android.os.Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                File downloadDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
                                File listAllFiles[] = downloadDir.listFiles();
                                if (listAllFiles != null && listAllFiles.length > 0) {
                                    for (File currentFile : listAllFiles) {
                                        if (currentFile.getName().endsWith("")) {
                                            Log.d("SOPOnline", "File name ==> " + currentFile.toString());
                                            if (currentFile.getName().substring(0,6).toString().equals("esmart")) {
                                                pdfView = (PDFView) findViewById(R.id.pdfWebView);
                                                pdfView.fromFile(currentFile).load();
                                            }
                                        }
                                    }
                                }
                            }
                        },5000
                );
            }
        }

    }

    private Boolean doInBackground(String url) {

        boolean flag = true;
        boolean downloading = true;

        try{

            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            String filename = URLUtil.guessFileName(url, null, MimeTypeMap.getFileExtensionFromUrl(url));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);

            long idDownload = manager.enqueue(request);

            DownloadManager.Query query = null;
            query = new DownloadManager.Query();
            Cursor c = null;

            if(query!=null) {
                query.setFilterByStatus(DownloadManager.STATUS_FAILED|DownloadManager.STATUS_PAUSED|DownloadManager.STATUS_SUCCESSFUL|
                        DownloadManager.STATUS_RUNNING|DownloadManager.STATUS_PENDING);
            } else {
                return flag;
            }
            c = manager.query(query);
            if( c.moveToFirst() ) {



                while (downloading)
                {
                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    Log.i ("SOPOnline","Downloading" + status);
                    Log.i ("SOPOnline","Downloading DownloadManager.STATUS_SUCCESSFUL" + DownloadManager.STATUS_SUCCESSFUL);
                    if (status == DownloadManager.STATUS_SUCCESSFUL)
                    {    Log.i ("SOPOnline","done");
                        downloading = false;
                        flag = true;
                        break;
                    }
                    if (status == DownloadManager.STATUS_FAILED)
                    {Log.i ("SOPOnline","Fail");
                        downloading = false;
                        flag = false;
                        break;
                    }
                    c.moveToNext();
                }
            }
            return flag;
        }
        catch (Exception e)
        {
            flag = false;
            return flag;
        }

//        try {
//            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//                request.setTitle("Vertretungsplan");
//                request.setDescription("wird heruntergeladen");
//            request.allowScanningByMediaScanner();
//            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//            String filename = URLUtil.guessFileName(url, null, MimeTypeMap.getFileExtensionFromUrl(url));
//            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
//            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//            long idDownload = manager.enqueue(request);
//
//            DownloadManager.Query query = null;
//            query = new DownloadManager.Query();
//
//            Cursor cursor = null;
//            if ( q)
//
//                Log.d("SOPOnline", "filename ==> " + filename.toString());
//
//
//        } catch (Exception e) {
//            Log.d("SOPOnline", "Download Manager ==> " + e.toString());
//        }
//
//        return false;
    }


    private void clearESmartFile() {

        File downloadDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        File listAllFiles[] = downloadDir.listFiles();
        if (listAllFiles != null && listAllFiles.length > 0) {
            for (File currentFile : listAllFiles) {
                if (currentFile.getName().endsWith("")) {

                    if (currentFile.getName().substring(0,6).toString().equals("esmart")) {
                        currentFile.delete();
                    }
                    // File absolute path

                    Log.e("SOPOnline", currentFile.getAbsolutePath());
                    // File Name
                    Log.e("SOPOnline", currentFile.getName());
                    Log.e("SOPOnline", currentFile.getName().substring(0, 6));

                }

            }
        }

    }


}
