package com.fancy.library.net.impl;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 拦截器
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public interface Iinterceptor extends Interceptor{

    @Override
    Response intercept(Chain chain) throws IOException;
}
