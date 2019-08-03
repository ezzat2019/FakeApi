package com.example.programmer.fakeapi.ui;

import androidx.paging.PagedList;

import com.example.programmer.fakeapi.models.Posts;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostHelberApi {
    @GET("posts")
    Call<PagedList<Posts>> getPosts();
}
