package com.fancy.library.net;

import android.content.Context;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 请求
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public abstract class BaseCallBack<T> implements Callback<T> {

    protected final int defaultFailCode = -1;

    protected final Context mContext;

    public BaseCallBack(@NonNull Context context) {
        this.mContext = context;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.raw().code() == 200) {
            onSuc(response);
        } else {//失败响应
            onFail(response, Error.buildError(response).getErrorMessage(), response.raw().code());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {//网络问题会走该回调
        onFail(null, Error.buildError(t).getErrorMessage(), defaultFailCode);
    }

    public abstract void onSuc(Response<T> response);

    public abstract void onFail(Response<T> response, String message, int failCode);

}
