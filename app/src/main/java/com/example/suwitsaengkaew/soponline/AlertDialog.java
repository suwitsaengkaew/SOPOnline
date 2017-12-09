package com.example.suwitsaengkaew.soponline;


import android.content.Context;
import android.content.DialogInterface;


/**
 * Created by suwitsaengkaew on 7/12/2017 AD.
 */

public class AlertDialog {

    android.app.AlertDialog.Builder objAlert;

    public void errorDialog(Context context, String strTitle, String strMessage) {

        objAlert = new android.app.AlertDialog.Builder(context);
        objAlert.setTitle(strTitle);
        objAlert.setMessage(strMessage);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        objAlert.show();

    } // errorDialog
}
