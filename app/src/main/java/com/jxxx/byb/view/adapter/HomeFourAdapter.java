package com.jxxx.byb.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.bean.ArticleCateListBean;
import com.jxxx.byb.utils.GlideImageLoader;

import java.util.List;

public class HomeFourAdapter extends BaseQuickAdapter<ArticleCateListBean.ListBean, BaseViewHolder> {

    public HomeFourAdapter(List<ArticleCateListBean.ListBean> data) {
        super(R.layout.item_home_four, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleCateListBean.ListBean item) {
        GlideImageLoader.loadImageViewRadius(mContext, ConstValues.BASE_URL+item.getImage(),15,helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_canyuren,"0人参与");
    }
}
