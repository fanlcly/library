package com.fancy.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fancy.library.R;
import com.fancy.library.utils.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;

/**
 * fragment的基类
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public abstract class BaseFragment extends Fragment {


    protected Activity mActivity;
    protected Context mApplicationContext;
    protected View mRootView;
    public ImmersionBar mImmersionBar;
    private ToastUtils mToast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mApplicationContext = context.getApplicationContext();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        setListener();
        initImmersionBar();
        mToast = ToastUtils.getInstance(mActivity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }

        mToast.destory();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            initImmersionBar();
        }
    }

    /**
     * 布局
     *
     * @return
     */
    protected abstract int setLayoutId();


    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        if (setStatusBarColor() == 0) {
            mImmersionBar = ImmersionBar.with(this)
                    .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                    .statusBarColor(R.color.colorPrimary);
        } else {
            mImmersionBar = ImmersionBar.with(this)
                    .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                    .statusBarColor(setStatusBarColor());
        }


        mImmersionBar.init();
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    public abstract int setStatusBarColor();


    /**
     * view与数据绑定
     */
    protected abstract void initView();

    /**
     * 设置监听
     */
    protected void setListener() {

    }


    /**
     * 初始化数据
     */
    protected void initData() {

    }


    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }
}
