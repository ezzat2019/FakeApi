package com.example.programmer.fakeapi.data_source;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.programmer.fakeapi.models.Posts;
import com.example.programmer.fakeapi.retrofit.RetrofitMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSourcePosts extends PageKeyedDataSource<Integer, Posts> {

    private static final Integer FIRST_ITEM = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Posts> callback) {

        RetrofitMain.getInstance().getPostHelberApi().getPosts().enqueue(new Callback<PagedList<Posts>>() {
            @Override
            public void onResponse(Call<PagedList<Posts>> call, Response<PagedList<Posts>> response) {
                callback.onResult(response.body(), null, null);
            }

            @Override
            public void onFailure(Call<PagedList<Posts>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Posts> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Posts> callback) {
        RetrofitMain.getInstance().getPostHelberApi().getPosts().enqueue(new Callback<PagedList<Posts>>() {
            @Override
            public void onResponse(Call<PagedList<Posts>> call, Response<PagedList<Posts>> response) {
                Integer i;
                List list = response.body();
                //  Log.d("sssss",list.size()+"  ff" + params.key);

                callback.onResult(response.body(), null);

            }

            @Override
            public void onFailure(Call<PagedList<Posts>> call, Throwable t) {

            }
        });
    }
}
