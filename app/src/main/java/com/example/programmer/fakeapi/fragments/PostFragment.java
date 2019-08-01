package com.example.programmer.fakeapi.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.fakeapi.CommentActivity;
import com.example.programmer.fakeapi.MainActivity;
import com.example.programmer.fakeapi.R;
import com.example.programmer.fakeapi.adapters.RecyclePostAdapter;
import com.example.programmer.fakeapi.models.Posts;
import com.example.programmer.fakeapi.ui.ItemClickInterface;
import com.example.programmer.fakeapi.view_models.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    // ui
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    public static android.widget.SearchView searchView;
    private TextView txtHead;


    // var
    private RecyclePostAdapter adapter;
    private MainViewModel mainViewModel;
    private static int id1;



    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtHead=view.findViewById(R.id.txt_head_posts);

        setUpProgessBar(view);

        setUpRecycle(view);

        setUpSearchView(view);

    }

    private void setUpSearchView(View view) {



        searchView=view.findViewById(R.id.post_search);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtHead.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                txtHead.setVisibility(View.VISIBLE);
                return false;
            }
        });

      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String s) {

              adapter.getFilter().filter(s);
              return false;
          }

          @Override
          public boolean onQueryTextChange(String s) {
              adapter.getFilter().filter(s);
              return false;
          }
      });
    }


    private void setUpProgessBar(View view) {
        progressBar = view.findViewById(R.id.progress_bar_post);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private void setUpRecycle(View view) {
        recyclerView = view.findViewById(R.id.rec_posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclePostAdapter(getContext());

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getPagedListLiveDataPosts().observe(this, new Observer<PagedList<Posts>>() {
            @Override
            public void onChanged(PagedList<Posts> posts) {
                adapter.submitList(posts);
                recyclerView.setAdapter(adapter);


            }
        });
        adapter.setOnItem(new ItemClickInterface() {
            @Override
            public void onClick(int pos) {

                Intent intent = new Intent(getContext(), CommentActivity.class);
                setId1(pos+1);
                startActivity(intent);
            }
        });

    }

    public static int getId1() {
        return id1;
    }

    public void setId1(int id22) {
        id1 = id22;
    }

}
