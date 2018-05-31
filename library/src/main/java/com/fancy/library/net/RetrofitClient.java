package com.fancy.library.net;

import android.util.Log;

import com.fancy.library.net.impl.InterceptorInterface;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
        if (!isDebug) {
            return getProOkHttpCilent(interceptorInterface, isDebug);
        } else {
            return getleakOkHttpClient(interceptorInterface, isDebug);
        }

    }

    private static OkHttpClient getleakOkHttpClient(InterceptorInterface interceptorInterface, boolean isDebug) {
        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
        SSLSocketFactory sslSocketFactory = null;

        try {
            // 这里直接创建一个不做证书串验证的TrustManager
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS"); // SSL
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.e("RetrofitClient", e.getMessage());
        }


        HttpLoggingInterceptor mLoggingInterceptor = createHttpLoggingInterceptor(isDebug);
        Interceptor mInterceptor = interceptorInterface.getInterceptor();


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(mLoggingInterceptor)
                .retryOnConnectionFailure(Boolean.FALSE)            // 失败时重新连接
                .connectTimeout(10, TimeUnit.SECONDS)               // 超时时间是15秒
                .addNetworkInterceptor(mInterceptor)           // token interceptor
                .sslSocketFactory(sslSocketFactory)
                .build();

        return client;
    }

    private static OkHttpClient getProOkHttpCilent(InterceptorInterface interceptorInterface, boolean isDebug) {
        HttpLoggingInterceptor mLoggingInterceptor = createHttpLoggingInterceptor(isDebug);
        Interceptor mInterceptor = interceptorInterface.getInterceptor();


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(mLoggingInterceptor)
                .retryOnConnectionFailure(Boolean.FALSE)            // 失败时重新连接
                .connectTimeout(10, TimeUnit.SECONDS)               // 超时时间是15秒
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
