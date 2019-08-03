package com.example.programmer.fakeapi.retrofit;

import com.example.programmer.fakeapi.ui.AlbumHelberApi;
import com.example.programmer.fakeapi.ui.CommentHelberApi;
import com.example.programmer.fakeapi.ui.PhotoHelberApi;
import com.example.programmer.fakeapi.ui.PostHelberApi;
import com.example.programmer.fakeapi.ui.TodoHelberApi;
import com.example.programmer.fakeapi.ui.UserHelperApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMain {
    public static final String URL_API = "https://jsonplaceholder.typicode.com/";
    private static final RetrofitMain ourInstance = new RetrofitMain();
    private Retrofit retrofit;
    private UserHelperApi userHelperApi;
    private AlbumHelberApi albumHelberApi;
    private CommentHelberApi commentHelberApi;
    private PhotoHelberApi photoHelberApi;
    private PostHelberApi postHelberApi;
    private TodoHelberApi todoHelberApi;

    private RetrofitMain() {
        retrofit = new Retrofit.Builder().baseUrl(URL_API).addConverterFactory(GsonConverterFactory.create())
                .build();

        userHelperApi = retrofit.create(UserHelperApi.class);
        albumHelberApi = retrofit.create(AlbumHelberApi.class);
        commentHelberApi = retrofit.create(CommentHelberApi.class);
        photoHelberApi = retrofit.create(PhotoHelberApi.class);
        postHelberApi = retrofit.create(PostHelberApi.class);
        todoHelberApi = retrofit.create(TodoHelberApi.class);


    }

    public static RetrofitMain getInstance() {
        return ourInstance;
    }

    public AlbumHelberApi getAlbumHelberApi() {
        return albumHelberApi;
    }

    public CommentHelberApi getCommentHelberApi() {
        return commentHelberApi;
    }

    public PhotoHelberApi getPhotoHelberApi() {
        return photoHelberApi;
    }

    public PostHelberApi getPostHelberApi() {
        return postHelberApi;
    }

    public TodoHelberApi getTodoHelberApi() {
        return todoHelberApi;
    }

    public UserHelperApi getUserHelperApi() {
        return userHelperApi;
    }
}
