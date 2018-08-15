package com.fancy.library.adapter;

import android.support.annotation.Nullable;

import com.fancy.library.R;
import com.fancy.library.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.library.recyclerviewhelper.base.BaseViewHolder;

import java.util.List;

/**
 * 单一显示的适配器
 *
 * @author fanlei
 * @version 1.0 2018\5\30 0030
 * @since JDK 1.7
 */
public class SingleAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public SingleAdapter(@Nullable List data) {
        super(R.layout.item_single, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        helper.setText(R.id.text_view, item.toString());
    }
}
