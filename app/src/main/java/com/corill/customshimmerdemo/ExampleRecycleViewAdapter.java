package com.corill.customshimmerdemo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by hhhhh on 2017/4/13.
 */

public class ExampleRecycleViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ExampleRecycleViewAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
    }
}
