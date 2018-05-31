package com.fancy.toolslibrary.net;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fancy.library.net.BaseCallBack;

import retrofit2.Response;

/**
 * 请求回调业务类
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public class MyCallBack<T> extends BaseCallBack<T> {

    public MyCallBack(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onSuc(Response response) {

    }

    @Override
    public void onFail(String message, int failCode) {

    }
}
