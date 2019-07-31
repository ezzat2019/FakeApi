package com.example.programmer.fakeapi.data_source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.programmer.fakeapi.fragments.PostFragment;
import com.example.programmer.fakeapi.models.Comments;
import com.example.programmer.fakeapi.retrofit.RetrofitMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSourceComment extends PageKeyedDataSource<Integer, Comments> {

    private static final Integer FIRST_ITEM = 1;



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Comments> callback) {

        Log.d("eeeee", PostFragment.getId1() + "");

        RetrofitMain.getInstance().getCommentHelberApi().getCommentOfPost(PostFragment.getId1()).enqueue(new Callback<PagedList<Comments>>() {
            @Override
            public void onResponse(Call<PagedList<Comments>> call, Response<PagedList<Comments>> response) {
                callback.onResult(response.body(), null, null);
            }

            @Override
            public void onFailure(Call<PagedList<Comments>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Comments> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Comments> callback) {
        RetrofitMain.getInstance().getCommentHelberApi().getCommentOfPost(PostFragment.getId1()).enqueue(new Callback<PagedList<Comments>>() {
            @Override
            public void onResponse(Call<PagedList<Comments>> call, Response<PagedList<Comments>> response) {



                callback.onResult(response.body(), null);
            }

            @Override
            public void onFailure(Call<PagedList<Comments>> call, Throwable t) {

            }
        });
    }
}
