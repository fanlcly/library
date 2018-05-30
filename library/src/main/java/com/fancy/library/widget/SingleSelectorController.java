package com.fancy.library.widget;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.fancy.library.R;
import com.fancy.library.adapter.SingleAdapter;
import com.fancy.library.impl.OnItemClickListener;
import com.fancy.library.recyclerviewhelper.Decoration.DividerItemDecoration;
import com.fancy.library.recyclerviewhelper.base.BaseQuickAdapter;

import java.util.List;

/**
 * 单选的弹窗控制器
 *
 * @author fanlei
 * @version 1.0 2018\5\30 0030
 * @since JDK 1.7
 */
public class SingleSelectorController<T> extends Dialog {


    private Activity mContext;
    private List<T> mList;
    private String mTitle;
    private OnItemClickListener onItemClickListener;

    public SingleSelectorController(@NonNull Activity context, List list, String title) {
        super(context,R.style.BottomDialog);
        mContext = context;
        mList = list;
        mTitle = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.controller_single_selector);    // 设置布局文件
        setCanceledOnTouchOutside(false);    // 设置点击对话框外的空白处不关闭对话框
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = Gravity.BOTTOM;
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);  // 设置对话框的大小
            window.setBackgroundDrawableResource(android.R.color.transparent);    // 设置对话框背景为透明
        }
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(mTitle);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.list_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, true));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        SingleAdapter adapter = new SingleAdapter(mList);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(mList.get(position));
                }
                dismiss();
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
