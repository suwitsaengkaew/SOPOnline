package com.example.suwitsaengkaew.soponline;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.github.barteksc.pdfviewer.PDFView;

public class DocumentFileActivity extends AppCompatActivity {

    private PDFView pdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentfile_activity);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }

        Log.d("SOPOnline", "Get Intent ==> " + getIntent().getExtras().getString("url") );
        String Url = getIntent().getExtras().getString("url");
        Uri uri = Uri.parse(Url);
        Log.d("SOPOnline", "Uri ==> " + uri.toString());
        pdfView = (PDFView) findViewById(R.id.pdfWebView);
        //pdfView.fromUri(uri).load();
        pdfView.fromAsset("demo.pdf").load();

    }


}
