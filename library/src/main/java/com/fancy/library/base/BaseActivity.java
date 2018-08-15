package com.fancy.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.fancy.library.R;
import com.fancy.library.utils.AppManager;
import com.fancy.library.utils.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;

/**
 * activity的基类
 *
 * @author fanlei
 * @version 1.0 2018\5\31 0031
 * @since JDK 1.7
 */
public abstract class BaseActivity extends Activity {

    protected Context mApplicationContext;
    protected Activity mActivity;
    protected static boolean isAppForeground;
    public ImmersionBar mImmersionBar;
    public ToastUtils mToast;
    private Bundle savedInstanceState;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        this.mApplicationContext = this.getApplicationContext();
        this.mActivity = this;
        this.setContentView(this.setLayoutView());
        AppManager.getAppManager().addActivity(this);
        this.initView();
        this.initData();
        this.initListen();
        //初始化沉浸式
        initImmersionBar();
        mToast = ToastUtils.getInstance(mActivity);
    }


    protected void initImmersionBar() {
        if (setStatusBarColor() == 0) {
            mImmersionBar = ImmersionBar.with(this)
                    .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                    .statusBarColor(R.color.colorPrimary);
        } else {
            if (setStatusBarColor() == R.color.white) {
                mImmersionBar = ImmersionBar.with(this)
                        .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                        .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                        .statusBarColor(setStatusBarColor());
            } else {
                mImmersionBar = ImmersionBar.with(this)
                        .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                        .statusBarColor(setStatusBarColor());
            }

        }
        mImmersionBar.init();
    }

    public abstract int setStatusBarColor();

    public abstract int setLayoutView();

    public abstract void initView();

    public void initData() {
    }

    protected void initListen() {
    }

    protected void onDestroy() {
        super.onDestroy();
        //销毁沉浸式
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        AppManager.getAppManager().finishActivity(this);
        mToast.destory();
    }

    protected void onResume() {
        super.onResume();
        isAppForeground = true;
    }

    protected void onPause() {
        super.onPause();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 无参数跳转
     *
     * @param cls
     */
    protected void openActivity(Class<?> cls) {
        openActivity(cls, new Intent());
    }

    /**
     * 有参数跳转
     *
     * @param cls
     * @param intent
     */
    protected void openActivity(Class<?> cls, Intent intent) {
        openActivity(mActivity, cls, intent);
//        overridePendingTransition(R.anim.activity_create_in, R.anim.activity_create_out);
    }

    /**
     * 界面跳转
     *
     * @param context
     * @param cls
     * @param intent
     */
    private void openActivity(Context context, Class<?> cls, Intent intent) {
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.activity_finish_in, R.anim.activity_finish_out);
    }

}
