package com.example.programmer.fakeapi.data_source.data_source_factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.programmer.fakeapi.data_source.DataSourceComment;
import com.example.programmer.fakeapi.data_source.DataSourcePosts;
import com.example.programmer.fakeapi.models.Comments;
import com.example.programmer.fakeapi.models.Posts;

public class DataSourceCommentsFactory extends DataSource.Factory<Integer, Comments> {
    private MutableLiveData<DataSourceComment> mutableLiveData = new MutableLiveData<DataSourceComment>();
    private DataSourceComment dataSourceComment;

    @NonNull
    @Override
    public DataSource<Integer, Comments> create() {
        dataSourceComment=new DataSourceComment();
        mutableLiveData.postValue(dataSourceComment);

        return dataSourceComment;
    }
}
