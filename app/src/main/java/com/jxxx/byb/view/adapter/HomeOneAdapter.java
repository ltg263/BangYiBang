package com.jxxx.byb.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.bean.ProductListBean;
import com.jxxx.byb.utils.GlideImageLoader;

import java.util.List;

public class HomeOneAdapter extends BaseQuickAdapter<ProductListBean.ListBean, BaseViewHolder> {

    public HomeOneAdapter(List<ProductListBean.ListBean> data) {
        super(R.layout.item_home_one, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductListBean.ListBean item) {
        GlideImageLoader.loadImageViewRadius(mContext, ConstValues.BASE_URL+item.getImage(),15,helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_title,item.getName())
                .setText(R.id.tv_canyuren,"0人参与")
                .setText(R.id.tv_feiyong,"￥"+item.getMoney());
    }
}
