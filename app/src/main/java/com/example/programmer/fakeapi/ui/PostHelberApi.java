package com.example.programmer.fakeapi.ui;

import com.example.programmer.fakeapi.models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostHelberApi {
    @GET("posts")
    Call<List<Posts>> getPosts();
}