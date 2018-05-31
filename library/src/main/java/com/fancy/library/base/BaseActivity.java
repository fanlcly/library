package com.fancy.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import com.fancy.library.R;
import com.fancy.library.utils.AppManager;
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
    protected String TAG_LOG;
    protected int mScreenWidth;
    protected int mScreenHeight;
    protected static boolean isAppForeground;
    public ImmersionBar mImmersionBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mApplicationContext = this.getApplicationContext();
        this.mActivity = this;
        this.TAG_LOG = this.getClass().getSimpleName();
        DisplayMetrics metric = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metric);
        this.mScreenWidth = metric.widthPixels;
        this.mScreenHeight = metric.heightPixels;
        this.setContentView(this.setLayoutView());
        AppManager.getAppManager().addActivity(this);
        this.initView();
        this.initData();
        this.initListen();
        //初始化沉浸式
        initImmersionBar();

    }


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
