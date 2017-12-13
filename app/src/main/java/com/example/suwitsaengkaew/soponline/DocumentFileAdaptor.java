package com.example.suwitsaengkaew.soponline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by suwitsaengkaew on 11/12/2017 AD.
 */

public class DocumentFileAdaptor extends BaseAdapter {

    private Context ObjContext;
    private String[] strFileName, strFileUrl;

    public DocumentFileAdaptor(Context objContext, String[] strFileName, String[] strFileUrl) {

        this.ObjContext = objContext;
        this.strFileName = strFileName;
        this.strFileUrl = strFileUrl;

    }

    @Override
    public int getCount() {
        return strFileUrl.length;
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
        View view = ObjLayoutInflater.inflate(R.layout.documentfile_view, parent, false);

        TextView documentFileText = (TextView) view.findViewById(R.id.documentFileText);
        documentFileText.setText(strFileName[position]);

        return view;
    }
}
