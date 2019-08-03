package com.example.programmer.fakeapi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.fakeapi.PhotoActivity;
import com.example.programmer.fakeapi.R;
import com.example.programmer.fakeapi.adapters.RecycleAlbumAdapter;
import com.example.programmer.fakeapi.models.Album;
import com.example.programmer.fakeapi.ui.ItemClickInterface;
import com.example.programmer.fakeapi.view_models.MainViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {
    public static android.widget.SearchView searchView;
    private static int idd;
    // ui
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtHead;
    // var
    private RecycleAlbumAdapter adapter;
    private MainViewModel mainViewModel;
    private List<Album> albums;

    public AlbumFragment() {
        // Required empty public constructor
    }

    public static int getIdd() {
        return idd;
    }

    public static void setIdd(int idd) {
        AlbumFragment.idd = idd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtHead = view.findViewById(R.id.txt_head_comment);
        albums = new ArrayList<>();

        setUpProgessBar(view);

        setUpRecycle(view);

        setUpSearchView(view);


    }

    private void setUpSearchView(View view) {


        searchView = view.findViewById(R.id.album_search);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtHead.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mainViewModel.getListLiveDataAlbum().observe(getActivity(), new Observer<List<Album>>() {
                    @Override
                    public void onChanged(List<Album> albums) {

                        adapter.setList(new ArrayList<Album>(albums));
                        recyclerView.setAdapter(adapter);
                    }
                });

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
        progressBar = view.findViewById(R.id.progress_bar_album);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private void setUpRecycle(View view) {
        recyclerView = view.findViewById(R.id.rec_album);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecycleAlbumAdapter();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getListLiveDataAlbum().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {

                adapter.setList(new ArrayList<Album>(albums));
                recyclerView.setAdapter(adapter);
            }
        });

        adapter.setOnItem(new ItemClickInterface() {
            @Override
            public void onClick(int pos) {
                setIdd(pos + 1);
                startActivity(new Intent(getContext(), PhotoActivity.class));


            }
        });

    }


}
