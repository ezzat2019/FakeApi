package com.example.programmer.fakeapi;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import androidx.appcompat.app.AlertDialog;

public class App extends Application {
    public static Boolean checkConnection(final Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() == null) {
            new AlertDialog.Builder(context)
                    .setMessage("please check internet connection")
                    .setPositiveButton("try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            checkConnection(context);

                        }
                    }).create().show();
            return false;


        } else {

            return true;
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
