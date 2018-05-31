package com.fancy.toolslibrary;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.fancy.library.base.BaseActivity;
import com.fancy.library.impl.OnItemClickListener;
import com.fancy.library.net.BaseCallBack;
import com.fancy.library.net.RetrofitClient;
import com.fancy.library.net.impl.Iinterceptor;
import com.fancy.library.net.impl.InterceptorInterface;
import com.fancy.library.utils.OptionsPickerViewHelper;
import com.fancy.library.utils.Timber;
import com.fancy.library.widget.AlertDialog;
import com.fancy.library.widget.LoadingDialog;
import com.fancy.library.widget.SingleSelectorController;
import com.fancy.toolslibrary.net.Api;
import com.fancy.toolslibrary.net.TokenInterceptor;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivity {

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public int setLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(mActivity).builder().setMsg("我被弹出来了").show();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingDialog dialog  = new LoadingDialog(mActivity);
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                },5000);
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> data =  Arrays.asList(getResources().getStringArray(R.array.dates));
                SingleSelectorController<String> controller = new SingleSelectorController<>(mActivity,data,"");
                controller.show();
                controller.setOnItemClickListener(new OnItemClickListener<String>() {
                    @Override
                    public void onItemClick(String entity) {
                        mToast.show(entity);
                    }
                });
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> data =  Arrays.asList(getResources().getStringArray(R.array.dates));
                OptionsPickerViewHelper.showWheelView(mActivity,data, (TextView) findViewById(R.id.btn4));
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit =  RetrofitClient.getInstance("http://www.sanxiachuanggu.com"
                        , new InterceptorInterface() {
                            @Override
                            public Iinterceptor getInterceptor() {
                                return new TokenInterceptor();
                            }
                        }
                        ,BuildConfig.DEBUG);

                final Api service = retrofit.create(Api.class);
                //配置参数
                final Call<HomeDataEntity> call = service.getIndex();
                call.enqueue(new BaseCallBack<HomeDataEntity>(mActivity) {
                                 @Override
                                 public void onSuc(Response<HomeDataEntity> response) {
                                     HomeDataEntity entity = response.body();
                                     List<NewsInfoEntity> l = entity.getTopic();
                                     Timber.i(l.get(0).getTitle());
                                 }

                                 @Override
                                 public void onFail(String message, int code) {

                                 }
                             }
                );


            }
        });

    }
}
