package com.example.programmer.fakeapi.ui;

import androidx.paging.PagedList;

import com.example.programmer.fakeapi.models.Comments;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommentHelberApi {
    @GET("comments?")
    Call<List<Comments>> getCommentsList(@Query("postId") int id);
    @GET("comments?")

    Call<PagedList<Comments>> getCommentOfPost(@Query("postId") int id);

}
