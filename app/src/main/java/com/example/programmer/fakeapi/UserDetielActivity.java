package com.example.programmer.fakeapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.programmer.fakeapi.fragments.UsersFragment;

public class UserDetielActivity extends AppCompatActivity {

    // ui
    private TextView txtId, txtName, txtUserName, txtEmail;
    private TextView txtStreet, txtSuite, txtCity, txtZipcode;
    private TextView txtPhone, txtWebsite, txtBs, txtComanyName, txtCatch;
    private int pos;


    // var
    private String lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Fade());
        getWindow().setExitTransition(new Fade());

        setContentView(R.layout.activity_user_detiel);

        pos = getIntent().getIntExtra("pos", -1);

        setupTextView();


        bindView();


    }

    private void bindView() {
        if (pos == -1)
            return;

        txtCatch.setText(UsersFragment.getUsersList().get(pos).getCompany().getCatchphrase());
        txtComanyName.setText(UsersFragment.getUsersList().get(pos).getCompany().getName());
        txtBs.setText(UsersFragment.getUsersList().get(pos).getCompany().getBs());
        txtWebsite.setText(UsersFragment.getUsersList().get(pos).getWebsite());
        txtPhone.setText(UsersFragment.getUsersList().get(pos).getPhone());
        txtZipcode.setText(UsersFragment.getUsersList().get(pos).getAddress().getZipcode());
        txtCity.setText(UsersFragment.getUsersList().get(pos).getAddress().getCity());
        txtSuite.setText(UsersFragment.getUsersList().get(pos).getAddress().getSuite());
        txtStreet.setText(UsersFragment.getUsersList().get(pos).getAddress().getStreet());
        txtUserName.setText(UsersFragment.getUsersList().get(pos).getUsername());
        txtName.setText(UsersFragment.getUsersList().get(pos).getName());
        txtId.setText(UsersFragment.getUsersList().get(pos).getId() + "");
        txtEmail.setText(UsersFragment.getUsersList().get(pos).getEmail());

    }

    private void setupTextView() {
        txtEmail = findViewById(R.id.txt_u_user_email_n2);
        txtId = findViewById(R.id.txt_u_id_n2);
        txtName = findViewById(R.id.txt_u_name_n2);
        txtUserName = findViewById(R.id.txt_u_user_name2);

        txtStreet = findViewById(R.id.txt_u_street_n2);
        txtSuite = findViewById(R.id.txt_u_suite_n2);
        txtCity = findViewById(R.id.txt_u_user_city_n2);
        txtZipcode = findViewById(R.id.txt_u_user_zipcode_n2);

        txtPhone = findViewById(R.id.txt_u_phone_n2);
        txtWebsite = findViewById(R.id.txt_u_website_n2);
        txtBs = findViewById(R.id.txt_u_user_bs_n2);
        txtComanyName = findViewById(R.id.txt_u_comp_name_n2);
        txtCatch = findViewById(R.id.txt_u_catch_n2);
    }


    public void showUserLoc(View view) {
        lat = UsersFragment.getUsersList().get(pos).getAddress().getGeo().getLat();
        lon = UsersFragment.getUsersList().get(pos).getAddress().getGeo().getLng();
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", lon + "," + lat);
        String url = builder.build().toString();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
