package com.example.programmer.fakeapi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.fakeapi.R;
import com.example.programmer.fakeapi.UserDetielActivity;
import com.example.programmer.fakeapi.adapters.RecycleUserAdapter;
import com.example.programmer.fakeapi.models.Users;
import com.example.programmer.fakeapi.view_models.MainViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment {

    private static List<Users> usersList;
    // ui
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtId, txtName, txtUserName, txtEmail;
    private View view1;
    // var
    private RecycleUserAdapter adapter;
    private MainViewModel mainViewModel;

    public UsersFragment() {
        // Required empty public constructor
    }

    public static List<Users> getUsersList() {
        return usersList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupText(view);


        setUpProgessBar(view);

        setUpRecycle(view);
    }

    private void setupText(View view) {
        view1 = view.findViewById(R.id.vv);
        txtEmail = view.findViewById(R.id.txt_u_user_email_n);
        txtId = view.findViewById(R.id.txt_u_id_n);
        txtName = view.findViewById(R.id.txt_u_name_n);
        txtUserName = view.findViewById(R.id.txt_u_user_name_n);
    }

    private void setUpProgessBar(View view) {
        progressBar = view.findViewById(R.id.progress_bar_user);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private void setUpRecycle(final View view) {
        recyclerView = view.findViewById(R.id.rec_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecycleUserAdapter();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getListLiveDataUsers().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                usersList = new ArrayList<>(users);
                adapter.setList(usersList);
                recyclerView.setAdapter(adapter);

            }
        });
        final Pair<View, String> pair = Pair.create(view1, "vv_us");
        final Pair<TextView, String> pair1 = Pair.create(txtEmail, "email_us");
        final Pair<TextView, String> pair2 = Pair.create(txtUserName, "user_name_us");
        final Pair<TextView, String> pair3 = Pair.create(txtName, "name_us");
        final Pair<TextView, String> pair4 = Pair.create(txtId, "id_us");

        adapter.setOnItem(new RecycleUserAdapter.Got() {
            @Override
            public void posss(int poss) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), UserDetielActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("pos", poss);
                startActivity(intent);

            }
        });

    }
}
