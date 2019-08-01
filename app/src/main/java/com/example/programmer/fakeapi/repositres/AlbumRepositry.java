package com.example.programmer.fakeapi.repositres;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.programmer.fakeapi.models.Album;
import com.example.programmer.fakeapi.retrofit.RetrofitMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepositry {
    private MutableLiveData<List<Album>> allAlbum;
    private static final AlbumRepositry ourInstance = new AlbumRepositry();

    public static AlbumRepositry getInstance() {
        return ourInstance;
    }

    private AlbumRepositry() {
        allAlbum=new MutableLiveData<>();
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

    public MutableLiveData<List<Album>> getAllAlbum() {
        return allAlbum;
    }
}
