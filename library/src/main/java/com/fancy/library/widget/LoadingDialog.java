package com.fancy.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancy.library.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2018\5\30 0030
 * @since JDK 1.7
 */
public class LoadingDialog {
    /**
     * 旋转动画的时间
     */
    static final int ROTATION_ANIMATION_DURATION = 1200;

    /**
     * 动画插值
     */
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();

    Window window;

    Dialog d;

    private ImageView imageView;

    //	private Animation animation;

    private TextView textView;

    private Context context;
    private final BounceLoadingView loadingView;

    @SuppressWarnings("deprecation")

    public LoadingDialog(Context context) {
        this(context,getBitMapIds());
    }

    private static List<Integer> getBitMapIds() {
        List<Integer> bitMapIds = new ArrayList<>();
        bitMapIds.add(R.drawable.orange);
        bitMapIds.add(R.drawable.purple);
        bitMapIds.add(R.drawable.red);
        bitMapIds.add(R.drawable.yellow);
        return bitMapIds;
    }


    public LoadingDialog(Context context,List<Integer> bitMapIds) {
        this.context = context;
        View view = View.inflate(context, R.layout.base_loading_view, null);
        loadingView = view.findViewById(R.id.loadingView);

        for (int i = 0; i < bitMapIds.size(); i++) {
            loadingView.addBitmap(bitMapIds.get(i));
        }

        loadingView.setShadowColor(Color.LTGRAY);
        loadingView.setDuration(700);

        d = new Dialog(context, R.style.TransDialog);// 加入样式
        d.setCanceledOnTouchOutside(false);
        window = d.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public void show() {
        loadingView.start();
        if (null != d && !d.isShowing()) {
            d.show();
        }
    }

    public void show(String content) {
        loadingView.start();
        if (null != d && !d.isShowing()) {
            textView.setText(content);
            d.show();
        }
    }

    public void dismiss() {
        loadingView.stop();
        if (d != null) {
            d.dismiss();
        }
    }


}
