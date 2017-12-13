package com.example.suwitsaengkaew.soponline;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DocumentListActivity extends AppCompatActivity {

    private DocumentListTABLE documentListTab;
    private DocumentFileTABLE documentFileTab;
    private String[] documentFileFileName;
    private String orgCodeGetIntent;
    private String[][] strDocumentList2D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentlist_activity);

        documentListTab = new DocumentListTABLE(this);
        documentFileTab = new DocumentFileTABLE(this);

        orgCodeGetIntent = getIntent().getExtras().getString("orgCode");
        // Log.d("SOPOnline", "Intent Extras ==> " + orgCodeGetIntent);
        strDocumentList2D = documentListTab.searchDocumentList1D(orgCodeGetIntent);

        // Log.d("SOPOnline", "Length of DocumentList ==> " + strDocumentList2D.length);

        createDocumentListView();
    }

    private String strFilter(String filter) {

        String url = "http://10.102.1.73/esmart7_files/";
        String[] filtered = filter.split("\\\\");

        for (int i = 0; i < filtered.length; i++) {
            if ((i > 1) && ( i < filtered.length -1)) {
                url = url + filtered[i] + "/";
            }
            else if (i > 1) {
                url = url + filtered[i];
                url = url + "/esmart.pdf";
            }
        }

        return url;
    }


    private void createDocumentListView() {

        DocumentListAdaptor documentlistAdt = new DocumentListAdaptor(DocumentListActivity.this, strDocumentList2D);
        ListView ObjListView = (ListView) findViewById(R.id.documentListlistview);
        ObjListView.setAdapter(documentlistAdt);

        ObjListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String DocId = strDocumentList2D[position][0].toString();
                documentFileFileName = documentFileTab.searchDocumentFile(DocId);

                String[] documentFile = new String[documentFileFileName.length];
                String Url = null;
                for (int i = 0; i < documentFileFileName.length; i++) {
                    documentFile[i] = strFilter(documentFileFileName[i].toString());
                    // Log.d("SOPOnline", "Document i ==> " + documentFile[i].toString());
                }

                if (documentFile.length == 1) {
                    Url = documentFile[0].toString();

                    Intent ObjIntentDocumentFileActivity = new Intent(DocumentListActivity.this, DocumentFileActivity.class);
                    ObjIntentDocumentFileActivity.putExtra("url", Url);
                    startActivity(ObjIntentDocumentFileActivity);
                }


            }
        });

    }
}
