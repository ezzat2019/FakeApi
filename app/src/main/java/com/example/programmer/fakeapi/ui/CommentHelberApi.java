package com.example.programmer.fakeapi.ui;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommentHelberApi {
    @GET("comments")
    Call<List<Comment>> getComments();
}
