package com.example.programmer.fakeapi.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.programmer.fakeapi.models.Users;
import com.example.programmer.fakeapi.retrofit.RetrofitMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<Users>> liveData;

    public MutableLiveData<List<Users>> getLiveData() {
        return liveData;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        liveData=new MutableLiveData<>();
        RetrofitMain.getInstance().getUserHelperApi().getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                liveData.postValue(response.body());


            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });

    }



}
