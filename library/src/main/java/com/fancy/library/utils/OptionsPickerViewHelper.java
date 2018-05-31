package com.fancy.library.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * 条件选择器的帮助类
 *
 * @author fanlei
 * @version 1.0 2018\5\30 0030
 * @since JDK 1.7
 */
public class OptionsPickerViewHelper {


    /**
     * 单列数据滚轮样式
     *
     * @param context
     * @param data
     * @param view
     */
    public  static <T> void showWheelView(final Context context, final List<T> data, final TextView view) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = data.get(options1).toString();
                view.setText(tx);
                Toast.makeText(context, tx, Toast.LENGTH_SHORT).show();
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setContentTextSize(18)//滚轮文字大小
                .setCyclic(false, false, false)//循环与否
                .build();
        pvOptions.setPicker(data);
        pvOptions.show();

    }


}
