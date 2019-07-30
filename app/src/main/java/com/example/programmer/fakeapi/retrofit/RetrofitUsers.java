package com.example.programmer.fakeapi.retrofit;

import com.example.programmer.fakeapi.ui.UserHelperApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUsers {
    private static final RetrofitUsers ourInstance = new RetrofitUsers();
private Retrofit retrofit;
public static final String URL_API="https://jsonplaceholder.typicode.com/";
private UserHelperApi userHelperApi;

    public static RetrofitUsers getInstance() {
        return ourInstance;
    }

    private RetrofitUsers() {
        retrofit=new Retrofit.Builder().baseUrl(URL_API).addConverterFactory(GsonConverterFactory.create())
                .build();

        userHelperApi=retrofit.create(UserHelperApi.class);



    }

    public UserHelperApi getUserHelperApi() {
        return userHelperApi;
    }
}
