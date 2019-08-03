package com.example.programmer.fakeapi.ui;

import com.example.programmer.fakeapi.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoHelberApi {
    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("photos?")
    Call<List<Photo>> getPhotosByAlbumId(@Query("albumId") int id);
}
