package com.example.programmer.fakeapi.repositres;

import androidx.lifecycle.MutableLiveData;

import com.example.programmer.fakeapi.models.Album;
import com.example.programmer.fakeapi.retrofit.RetrofitMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepositry {
    private static final AlbumRepositry ourInstance = new AlbumRepositry();
    private MutableLiveData<List<Album>> allAlbum;

    private AlbumRepositry() {
        allAlbum = new MutableLiveData<>();
        RetrofitMain.getInstance().getAlbumHelberApi().getAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                allAlbum.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    public static AlbumRepositry getInstance() {
        return ourInstance;
    }

    public MutableLiveData<List<Album>> getAllAlbum() {
        return allAlbum;
    }
}
