package com.jxxx.byb.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;
import com.jxxx.byb.utils.GlideImageLoader;

import java.util.List;

public class HomeOneAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HomeOneAdapter(List<String> data) {
        super(R.layout.item_home_one, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideImageLoader.loadImageViewRadius(mContext,item,15,helper.getView(R.id.iv_img));
    }
}
