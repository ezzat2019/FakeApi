package com.example.programmer.fakeapi.data_source.data_source_factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.programmer.fakeapi.data_source.DataSourcePosts;
import com.example.programmer.fakeapi.models.Posts;

public class DataSourcePostsFactory extends DataSource.Factory<Integer, Posts> {
    private MutableLiveData<DataSourcePosts> mutableLiveData = new MutableLiveData<DataSourcePosts>();
    private DataSourcePosts sourcePosts;

    @NonNull
    @Override
    public DataSource<Integer, Posts> create() {
        sourcePosts = new DataSourcePosts();
        mutableLiveData.postValue(sourcePosts);

        return sourcePosts;
    }
}
