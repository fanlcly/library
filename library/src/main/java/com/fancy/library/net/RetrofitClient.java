package com.fancy.library.net;

import com.fancy.library.net.impl.InterceptorInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit build类
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public class RetrofitClient {
    private static volatile Retrofit mRetrofit;

    public static Retrofit getInstance(String baseUrl, InterceptorInterface interceptorInterface, boolean isDebug) {
        if (mRetrofit == null) {
            synchronized (RetrofitClient.class) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .client(getOkHttpClient(interceptorInterface, isDebug))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    private static OkHttpClient getOkHttpClient(InterceptorInterface interceptorInterface, boolean isDebug) {
        return getProOkHttpCilent(interceptorInterface, isDebug);
    }


    private static OkHttpClient getProOkHttpCilent(InterceptorInterface interceptorInterface, boolean isDebug) {
        HttpLoggingInterceptor mLoggingInterceptor = createHttpLoggingInterceptor(isDebug);
        Interceptor mInterceptor = interceptorInterface.getInterceptor();


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(mLoggingInterceptor)
                .retryOnConnectionFailure(Boolean.TRUE)            // 失败时重新连接
                .connectTimeout(15, TimeUnit.SECONDS)               // 超时时间是15秒
                .addNetworkInterceptor(mInterceptor)           // token interceptor
                .build();


        return client;
    }

    /**
     * log interceptor
     *
     * @return
     */
    private static HttpLoggingInterceptor createHttpLoggingInterceptor(boolean isDebug) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }


}
