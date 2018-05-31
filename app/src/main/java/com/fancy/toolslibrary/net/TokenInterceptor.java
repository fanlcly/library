package com.fancy.toolslibrary.net;

import android.text.TextUtils;

import com.fancy.library.net.impl.Iinterceptor;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public class TokenInterceptor implements Iinterceptor {
    public String TOKEN_KEY = "token";

    public String ZOKEN_KEY="zoken";
    @Override
    public Response intercept(Chain chain) throws IOException {
        String mToken = "123456";

        Request originalRequest = chain.request();
        if (TextUtils.isEmpty(mToken) || alreadyHasAuthorizationHeader(originalRequest)) {
            return chain.proceed(originalRequest);
        }
        Request authorised = originalRequest.newBuilder()
                .header("Authorization", "Bearer "+mToken)
                .header(ZOKEN_KEY,mToken)
                //.header(SIGNATURE,signature)
                .build();

        return chain.proceed(authorised);
    }

    @SuppressWarnings("JavaDoc")
    private boolean alreadyHasAuthorizationHeader(Request request) {
        if (request != null) {
            if (request.headers() != null && request.header(TOKEN_KEY) != null) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
