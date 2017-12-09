package com.example.suwitsaengkaew.soponline;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by suwitsaengkaew on 9/12/2017 AD.
 */
public class DocumentListAdaptor  extends BaseAdapter {

    private Context Objcontext;
    private String[] strNumber;
    private String[] strSubject;

    public DocumentListAdaptor(Context context, String[][] strOrgCode) {

        this.Objcontext = context;
        this.strNumber = new String[strOrgCode.length];
        this.strSubject = new String[strOrgCode.length];

        Log.d("SOPOnline", "Array length ==> " + strOrgCode.length);
        for (int i = 0; i < strOrgCode.length; i++) {

            this.strNumber[i] = strOrgCode[i][0];
            this.strSubject[i] = strOrgCode[i][1];

            Log.d("SOPOnline", "Array Length 2 ==> " + strOrgCode[i].length);
        }



    }

    @Override
    public int getCount() {
        return strSubject.length;
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

        LayoutInflater objLayoutInflater = (LayoutInflater) Objcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.documentlist_view, parent, false);

        //Set Text to documentListText
        TextView documentListText = (TextView) view.findViewById(R.id.documentListText);
        documentListText.setText("[ " + strNumber[position] + " ] - " + strSubject[position]);
        return view;
    }
}
