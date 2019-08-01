package com.example.programmer.fakeapi.repositres;

import androidx.lifecycle.MutableLiveData;

import com.example.programmer.fakeapi.fragments.AlbumFragment;
import com.example.programmer.fakeapi.models.Album;
import com.example.programmer.fakeapi.models.Photo;
import com.example.programmer.fakeapi.retrofit.RetrofitMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepositry {
    private MutableLiveData<List<Photo>> allPhoto;
    private static final PhotoRepositry ourInstance = new PhotoRepositry();

    public static PhotoRepositry getInstance() {
        return ourInstance;
    }

    private PhotoRepositry() {
        allPhoto=new MutableLiveData<>();
        RetrofitMain.getInstance().getPhotoHelberApi().getPhotosByAlbumId(AlbumFragment.getIdd()).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                allPhoto.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Photo>> getAllAlbum() {
        return allPhoto;
    }
}
