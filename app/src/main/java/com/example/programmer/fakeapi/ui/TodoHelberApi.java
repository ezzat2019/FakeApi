package com.example.programmer.fakeapi.ui;

import com.example.programmer.fakeapi.models.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoHelberApi {
    @GET("todos")
    Call<List<Todo>> getTodos();
}
