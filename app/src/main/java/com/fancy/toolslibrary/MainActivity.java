package com.fancy.toolslibrary;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fancy.library.impl.OnItemClickListener;
import com.fancy.library.net.RetrofitClient;
import com.fancy.library.net.impl.Iinterceptor;
import com.fancy.library.net.impl.InterceptorInterface;
import com.fancy.library.utils.OptionsPickerViewHelper;
import com.fancy.library.widget.AlertDialog;
import com.fancy.library.widget.LoadingDialog;
import com.fancy.library.widget.SingleSelectorController;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(MainActivity.this).builder().setMsg("我被弹出来了").show();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingDialog dialog  = new LoadingDialog(MainActivity.this);
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
                SingleSelectorController<String> controller = new SingleSelectorController<>(MainActivity.this,data,"");
                controller.show();
                controller.setOnItemClickListener(new OnItemClickListener<String>() {
                    @Override
                    public void onItemClick(String entity) {
                        Toast.makeText(MainActivity.this,entity,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitClient.getInstance("www.sanxiachuanggu.com/servant/pub/index", new InterceptorInterface() {
                    @Override
                    public Iinterceptor getInterceptor() {
                        return null;
                    }
                },BuildConfig.DEBUG);
            }
        });
    }
}
