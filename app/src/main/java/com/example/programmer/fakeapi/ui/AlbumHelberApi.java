package com.example.programmer.fakeapi.ui;

import com.example.programmer.fakeapi.models.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumHelberApi {
    @GET("albums")
    Call<List<Album>> getAlbums();
}
