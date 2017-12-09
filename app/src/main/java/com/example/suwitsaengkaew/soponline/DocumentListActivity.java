package com.example.suwitsaengkaew.soponline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DocumentListActivity extends AppCompatActivity {

    private DocumentListTABLE documentListTab;
    private String orgCodeGetIntent;
    private String[][] strDocumentList2D;
    private String[] strDocumentList1D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentlist_activity);

        documentListTab = new DocumentListTABLE(this);
        orgCodeGetIntent = getIntent().getExtras().getString("orgCode");
        Log.d("SOPOnline", "Intent Extras ==> " + orgCodeGetIntent);
        strDocumentList2D = documentListTab.searchDocumentList1D(orgCodeGetIntent);
        //strDocumentList1D = documentListTab.searchDocumentList1D("1017000003269");
        Log.d("SOPOnline", "Length of DocumentList ==> " + strDocumentList2D.length);
        // checkArray2D(strDocumentList2D);
        // checkArray1D(strDocumentList1D);

        createDocumentListView();
    }

    private void checkArray1D(String[] strDocumentList1D) {
        Log.d("SOPOnline", "Check strLength ==> " + strDocumentList1D.length);
    }

    private void checkArray2D(String[][] strDocumentList) {

        Log.d("SOPOnline", "Check strLength ==> " + strDocumentList.length);
//        for (int i = 0; i < strDocumentList.length; i++) {
//
//            for (int y = 0; y < strDocumentList[i].length; y++) {
//
//                Log.d("SOPOnline", "List ==> " + strDocumentList[i][y].toString());
//
//            }
//
//        }
    }

    private void createDocumentListView() {

        DocumentListAdaptor documentlistAdt = new DocumentListAdaptor(DocumentListActivity.this, strDocumentList2D);
        ListView ObjListView = (ListView) findViewById(R.id.documentListlistview);
        ObjListView.setAdapter(documentlistAdt);

        ObjListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alert = new AlertDialog();
                alert.errorDialog(DocumentListActivity.this,strDocumentList2D[position][0], strDocumentList2D[position][1]);
            }
        });

    }
}
