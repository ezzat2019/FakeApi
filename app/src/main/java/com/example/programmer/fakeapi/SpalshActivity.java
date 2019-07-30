package com.example.programmer.fakeapi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class SpalshActivity extends AppCompatActivity {
    // ui
    private TextView txt;

    //  var
    private Thread thread;
    private long time = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);

        creattAnimatinText();

        customCheckConnection(SpalshActivity.this);


    }

    @Override
    public void onBackPressed() {

    }

    private void gotoMainAsreen(final long i) {
        time = i;
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(time);
                startActivity(intent);
                finish();

            }
        }).start();

    }

    private void creattAnimatinText() {
        txt = findViewById(R.id.txt_main);
        txt.setAnimation(AnimationUtils.loadAnimation(this, R.anim.text_animm));
    }


    private Boolean customCheckConnection(final Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() == null) {
            new AlertDialog.Builder(context)
                    .setMessage("please check internet connection")
                    .setPositiveButton("try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            customCheckConnection(context);

                        }
                    }).create().show();
            return false;


        } else {


            gotoMainAsreen(2000);
            return true;
        }

    }
}
