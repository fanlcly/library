package com.fancy.toolslibrary.net;

import com.fancy.toolslibrary.HomeDataEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public interface Api {
    @GET("/servant/pub/index/")
    Call<HomeDataEntity> getIndex();
}
