package com.fancy.library.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.fancy.library.R;

import java.util.Calendar;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2018\5\30 0030
 * @since JDK 1.7
 */
public class PickerViewHelper {
    private static final String EMPTY = "";    // 空字符串
    private static float lineSpacingMultiplier = 2F;  // 条目间距倍数 默认1.6
    private Activity mActivity;
    private TimePickerView pvTime;

    public PickerViewHelper(Activity activity) {
        mActivity = activity;

    }

    // 获取通用的样式，从当天到2088-12-31
    public void getCommonView(OnTimeSelectListener listener, Calendar select) {
        Calendar startDate = Calendar.getInstance(); // 最小值
        Calendar endDate = Calendar.getInstance(); // 最大值
        endDate.set(2088, 11, 31);  // 最小值
        getCommonView(listener, startDate, endDate, select);
    }

    // 获取生日的日期区间 从1900到当天
    public void getBirthdayView(OnTimeSelectListener listener,Calendar select) {
        Calendar startDate = Calendar.getInstance(); // 最小值
        startDate.set(1900, 0, 31);  // 最小值
        Calendar endDate = Calendar.getInstance(); // 最大值
        getCommonView(listener, startDate, endDate, select);
    }

    /**
     * 获取通用的样式
     *
     * @param listener 回调监听
     * @param start    开始
     * @param end      结束
     * @param select   默认选中
     */
    public void getCommonView(OnTimeSelectListener listener, Calendar start, Calendar end, Calendar select) {
        // 日期选择器
        pvTime = new TimePickerBuilder(mActivity, listener)
                .isCyclic(false)  //是否循环滚动
                .setType(new boolean[]{true, true, true, false, false, false})   // 只显示年月日
                .isCenterLabel(true)  // 每项item全部都带有label
                .setContentTextSize(20)    // 滚轮文字大小
                .setTitleBgColor(ContextCompat.getColor(mActivity, R.color.bg_common))  // 标题背景颜色
                .setCancelColor(ContextCompat.getColor(mActivity, R.color.text_666)) // 取消按钮文字颜色
                .setSubmitColor(ContextCompat.getColor(mActivity, R.color.text_037BFF))   // 确定按钮文字颜色
                .setDividerColor(ContextCompat.getColor(mActivity, R.color.divider_common))  // 分隔线颜色
                .setRangDate(start, end)
                .setOutSideCancelable(false)
                .setLabel(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)
                .setDate(select)   // 默认选中
                .setLineSpacingMultiplier(lineSpacingMultiplier)
                .build();
        pvTime.show();
    }


}
