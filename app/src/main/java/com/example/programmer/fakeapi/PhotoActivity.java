package com.example.programmer.fakeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.programmer.fakeapi.adapters.RecycleCommentAdapter;
import com.example.programmer.fakeapi.adapters.view_pager.RecyclePhotoAdapter;
import com.example.programmer.fakeapi.models.Comments;
import com.example.programmer.fakeapi.models.Photo;
import com.example.programmer.fakeapi.ui.ItemClickInterface;
import com.example.programmer.fakeapi.view_models.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    // ui
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    // var
    private RecyclePhotoAdapter adapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        setUpProgessBar();


        setUpRecycle();

    }
    private void setUpProgessBar() {
        progressBar = findViewById(R.id.progress_bar_photo);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 500);
    }

    private void setUpRecycle() {
        recyclerView = findViewById(R.id.rec_photo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new RecyclePhotoAdapter(getApplication());

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getListLiveDataPhoto().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                adapter.setList(new ArrayList<Photo>(photos));
                recyclerView.setAdapter(adapter);
            }
        });
        adapter.setOnItem(new ItemClickInterface() {
            @Override
            public void onClick(int pos) {

                Toast.makeText(getApplicationContext(), pos + "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
