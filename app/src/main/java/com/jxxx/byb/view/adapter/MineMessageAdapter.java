package com.jxxx.byb.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;

import java.util.List;

public class MineMessageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MineMessageAdapter(List<String> data) {
        super(R.layout.item_mine_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

}
