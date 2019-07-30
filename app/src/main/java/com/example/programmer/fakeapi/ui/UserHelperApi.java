package com.example.programmer.fakeapi.ui;

import com.example.programmer.fakeapi.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserHelperApi {
    @GET("users")
    Call<List<Users>> getUsers();
}
