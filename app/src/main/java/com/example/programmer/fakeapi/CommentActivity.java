package com.example.programmer.fakeapi;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.fakeapi.adapters.RecycleCommentAdapter;
import com.example.programmer.fakeapi.models.Comments;
import com.example.programmer.fakeapi.ui.ItemClickInterface;
import com.example.programmer.fakeapi.view_models.MainViewModel;

public class CommentActivity extends AppCompatActivity {
    // ui
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    // var
    private RecycleCommentAdapter adapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_comment);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


        setUpProgessBar();


        setUpRecycle();
    }

    private void setUpProgessBar() {
        progressBar = findViewById(R.id.progress_bar_comment);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 500);
    }

    private void setUpRecycle() {
        recyclerView = findViewById(R.id.rec_comment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new RecycleCommentAdapter(getApplicationContext());

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getPagedListLiveDataComments().observe(this, new Observer<PagedList<Comments>>() {
            @Override
            public void onChanged(PagedList<Comments> comments) {
                adapter.submitList(comments);
                recyclerView.setAdapter(adapter);


            }
        });
        adapter.setOnItem(new ItemClickInterface() {
            @Override
            public void onClick(int pos) {

                Toast.makeText(CommentActivity.this, pos + "", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
