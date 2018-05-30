package com.fancy.library.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.fancy.library.R;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * 图片加载帮助类
 *
 * @author fanlei
 * @version 1.0 2018\5\30 0030
 * @since JDK 1.7
 */
public class ImageLoaderHelper {

    private ImageLoaderHelper() {
        throw new UnsupportedOperationException("no instantiate");
    }


    /**
     * 加载网络图片,默认加载图
     * @param mContext
     * @param view
     * @param url
     */
    public static void setUrlImage(Context mContext, ImageView view, String url) {
        setUrlImage(mContext,view,url,R.color.bg_default_img);
    }

    /**
     * 加载网络图片,自定义加载图
     * @param mContext
     * @param view
     * @param url
     * @param defaultImageId
     */
    public static void setUrlImage(Context mContext, ImageView view, String url,int defaultImageId) {
        if (TextUtils.isEmpty(url)) {
            url = "0";
        }

        Picasso.with(mContext)
                .load(url)
                .placeholder(defaultImageId)
                .error(defaultImageId)
                .into(view);

    }


    /**
     * 加载资源文件图片,默认加载图
     * @param mContext
     * @param view
     * @param imgId
     */
    public static void setResImage(Context mContext, ImageView view, int imgId) {
        setResImage(mContext,view,imgId,R.color.bg_default_img);
    }


    /**
     * 加载资源文件图片,自定义加载图
     * @param mContext
     * @param view
     * @param imgId
     * @param defaultImageId
     */
    public static void setResImage(Context mContext, ImageView view, int imgId,int defaultImageId) {
        Picasso.with(mContext)
                .load(imgId)
                .placeholder(defaultImageId)
                .error(defaultImageId)
                .into(view);
    }

    /**
     * 显示本地图片
     *
     * @param mContext
     * @param view
     * @param file
     */
    public static void setLocalImage(Context mContext, ImageView view, File file) {
        setLocalImage(mContext,view,file,R.color.bg_default_img);
    }
    /**
     * 显示本地图片
     *
     * @param mContext
     * @param view
     * @param file
     */
    public static void setLocalImage(Context mContext, ImageView view, File file,int defaultImageId) {
        Picasso.with(mContext)
                .load(file)
                .placeholder(defaultImageId)
                .error(defaultImageId)
                .into(view);
    }


}
