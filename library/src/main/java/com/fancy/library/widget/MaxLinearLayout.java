package com.fancy.library.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 最多显示屏幕的60%的线性布局
 * @author fanlei
 * @version 1.0 2017/12/26
 * @since JDK 1.7
 */
public class MaxLinearLayout extends LinearLayout {

    public MaxLinearLayout(Context context) {
        super(context);
    }

    public MaxLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int max = (int) (getResources().getDisplayMetrics().heightPixels * 0.6);
        if (MeasureSpec.getSize(heightMeasureSpec) > max) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
