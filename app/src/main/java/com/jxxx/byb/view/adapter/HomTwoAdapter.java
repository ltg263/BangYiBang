package com.jxxx.byb.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;
import com.jxxx.byb.utils.GlideImageLoader;

import java.util.List;

public class HomTwoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HomTwoAdapter(List<String> data) {
        super(R.layout.item_home_two, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_no,"#"+helper.getLayoutPosition());
    }
}
