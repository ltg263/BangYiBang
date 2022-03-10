package com.jxxx.byb.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;
import com.jxxx.byb.bean.OrderPreviewBean;
import com.jxxx.byb.utils.GlideImageLoader;

import java.util.List;

public class ShopImageAdapter extends BaseQuickAdapter<OrderPreviewBean.PreviewOrderDTOBean.OrderDetailListBean, BaseViewHolder> {

    public ShopImageAdapter(List<OrderPreviewBean.PreviewOrderDTOBean.OrderDetailListBean> data) {
        super(R.layout.item_image_add, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderPreviewBean.PreviewOrderDTOBean.OrderDetailListBean item) {
            GlideImageLoader.loadImageAndDefault(mContext,item.getPreviewOrderSpuDTO().getIconUrl(), helper.getView(R.id.image_view));
            helper.setVisible(R.id.iv_delete,false);
    }

}
