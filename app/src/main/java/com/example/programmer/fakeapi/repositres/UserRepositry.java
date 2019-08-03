package com.example.programmer.fakeapi.repositres;

import androidx.lifecycle.MutableLiveData;

import com.example.programmer.fakeapi.models.Users;
import com.example.programmer.fakeapi.retrofit.RetrofitMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositry {
    private static final UserRepositry ourInstance = new UserRepositry();
    private MutableLiveData<List<Users>> allAlbum;

    private UserRepositry() {
        allAlbum = new MutableLiveData<>();
        RetrofitMain.getInstance().getUserHelperApi().getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                allAlbum.postValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }

    public static UserRepositry getInstance() {
        return ourInstance;
    }

    public MutableLiveData<List<Users>> getAllAlbum() {
        return allAlbum;
    }
}
