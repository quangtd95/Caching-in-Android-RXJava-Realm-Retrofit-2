package com.quangtd95.cachingrealm_rxjava_retrofit_2.services.core;

import com.quangtd95.cachingrealm_rxjava_retrofit_2.global.GlobalDef;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public abstract class DataCallBack<T> implements Callback<T>{
    public abstract void onSuccess(T response);
    public abstract void onError(String error);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(t.getLocalizedMessage());
    }
}
