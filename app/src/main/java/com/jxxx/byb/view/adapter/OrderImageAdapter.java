package com.jxxx.byb.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;
import com.jxxx.byb.bean.OrderHistoryDetailBean;
import com.jxxx.byb.utils.GlideImageLoader;

import java.util.List;

public class OrderImageAdapter extends BaseQuickAdapter<OrderHistoryDetailBean.OrderDetailListBean, BaseViewHolder> {

    public OrderImageAdapter(List<OrderHistoryDetailBean.OrderDetailListBean> data) {
        super(R.layout.item_image_add, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderHistoryDetailBean.OrderDetailListBean item) {
            GlideImageLoader.loadImageAndDefault(mContext,item.getUserOrderSpuDTO().getIconUrl(), helper.getView(R.id.image_view));
            helper.setVisible(R.id.iv_delete,false);
    }

}
