package com.example.programmer.fakeapi.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.programmer.fakeapi.data_source.DataSourceComment;
import com.example.programmer.fakeapi.data_source.DataSourcePosts;
import com.example.programmer.fakeapi.data_source.data_source_factory.DataSourceCommentsFactory;
import com.example.programmer.fakeapi.data_source.data_source_factory.DataSourcePostsFactory;
import com.example.programmer.fakeapi.models.Album;
import com.example.programmer.fakeapi.models.Comments;
import com.example.programmer.fakeapi.models.Photo;
import com.example.programmer.fakeapi.models.Posts;
import com.example.programmer.fakeapi.models.Users;
import com.example.programmer.fakeapi.repositres.AlbumRepositry;
import com.example.programmer.fakeapi.repositres.PhotoRepositry;
import com.example.programmer.fakeapi.repositres.UserRepositry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {


    private LiveData<PagedList<Posts>> pagedListLiveDataPosts;
    private LiveData<List<Album>> listLiveDataAlbum;
    private LiveData<List<Users>> listLiveDataUsers;
    private LiveData<List<Photo>> listLiveDataPhoto;
    private LiveData<PagedList<Comments>> pagedListLiveDataComments;

    public MainViewModel(@NonNull Application application) {
        super(application);

        DataSourcePosts.Factory factory = new DataSourcePostsFactory();
        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true)
                .setInitialLoadSizeHint(4)
                .setPageSize(10)
                .setPrefetchDistance(4).build();
        pagedListLiveDataPosts = new LivePagedListBuilder<Integer, Posts>(factory, config).build();

        DataSourceComment.Factory dataSourceComment = new DataSourceCommentsFactory();
        pagedListLiveDataComments = new LivePagedListBuilder<Integer, Comments>(dataSourceComment, config).build();


    }

    public LiveData<List<Users>> getListLiveDataUsers() {
        listLiveDataUsers = UserRepositry.getInstance().getAllAlbum();
        return listLiveDataUsers;
    }

    public LiveData<List<Photo>> getListLiveDataPhoto() {
        listLiveDataPhoto = PhotoRepositry.getInstance().getAllAlbum();
        return listLiveDataPhoto;
    }

    public LiveData<List<Album>> getListLiveDataAlbum() {
        listLiveDataAlbum = AlbumRepositry.getInstance().getAllAlbum();
        return listLiveDataAlbum;
    }

    public LiveData<PagedList<Comments>> getPagedListLiveDataComments() {
        return pagedListLiveDataComments;
    }

    public LiveData<PagedList<Posts>> getPagedListLiveDataPosts() {
        return pagedListLiveDataPosts;
    }


}
