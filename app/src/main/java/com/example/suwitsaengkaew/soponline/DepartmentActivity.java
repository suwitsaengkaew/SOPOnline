package com.example.suwitsaengkaew.soponline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DepartmentActivity extends AppCompatActivity {

    private DepartmentTABLE ObjDepartmentTable;
    private String[] strOrg_Code, strOrg_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.department_activity);


        ObjDepartmentTable = new DepartmentTABLE(this);

        strOrg_Code = ObjDepartmentTable.orgCode();
        strOrg_Name = ObjDepartmentTable.orgName();

        createDepartmentListView();

    }

    private void createDepartmentListView() {

        DepartmentAdaptor ObjDepartmentAdaptor = new DepartmentAdaptor(DepartmentActivity.this, strOrg_Code, strOrg_Name);
        ListView ObjDepartmentListView = (ListView) findViewById(R.id.departmentList);
        ObjDepartmentListView.setAdapter(ObjDepartmentAdaptor);

        ObjDepartmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent ObjIntentDocumentListActivity = new Intent(DepartmentActivity.this, DocumentListActivity.class);
                ObjIntentDocumentListActivity.putExtra("orgCode", strOrg_Code[position].toString());
                startActivity(ObjIntentDocumentListActivity);

            }
        });

    }
}
