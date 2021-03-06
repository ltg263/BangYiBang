package com.jxxx.byb.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;
import com.jxxx.byb.base.CommodityCategory;

import java.util.List;

public class HomeCategoryParentAdapter extends BaseQuickAdapter<CommodityCategory, BaseViewHolder> {
    int curPos = 0;

    public HomeCategoryParentAdapter(List<CommodityCategory> data) {
        super(R.layout.item_type_category_parent, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityCategory item) {
        helper.setText(R.id.tv_name,item.getCateName()).setVisible(R.id.view,false);
        if(curPos==helper.getLayoutPosition()){
            helper.setVisible(R.id.view,true);
        }
    }

    public void setCurPos(int curPos) {
        this.curPos = curPos;
    }
}
