package com.example.suwitsaengkaew.soponline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by suwitsaengkaew on 7/12/2017 AD.
 */

public class DepartmentAdaptor extends BaseAdapter {

    private Context ObjContext;
    private String[] strCode, strName;

    public DepartmentAdaptor(Context context, String[] strOrgCode, String[] strOrgName) {

        this.ObjContext = context;
        this.strCode = strOrgCode;
        this.strName = strOrgName;

    }

    @Override
    public int getCount() {
        return strCode.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater ObjLayoutInflater = (LayoutInflater) ObjContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View departmentView = ObjLayoutInflater.inflate(R.layout.departmentlist_view, parent, false);

        TextView sectionText = (TextView) departmentView.findViewById(R.id.sectionText);
        sectionText.setText(strName[position]);

        return departmentView;
    }
}
