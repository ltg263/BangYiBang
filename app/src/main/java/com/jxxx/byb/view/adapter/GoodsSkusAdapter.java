package com.jxxx.byb.view.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.R;
import com.jxxx.byb.base.ShopInfoData;
import com.jxxx.byb.view.activity.ShopDetailsActivity;

import java.util.List;

public class GoodsSkusAdapter extends BaseQuickAdapter<ShopInfoData.SkusBean, BaseViewHolder> {
    String spuSupplyType;
    @Override
    protected void convert(BaseViewHolder helper, ShopInfoData.SkusBean data) {
//        GlideImageLoader.loadImageViewRadius(mContext,iconUrl,30,helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_type,data.getSkuName()).setText(R.id.tv_price,"￥"+data.getPriceInfo().getPrice()+data.getPriceInfo().getUnit())
        .addOnClickListener(R.id.iv_add);
        helper.getView(R.id.rl_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopDetailsActivity.startActivityIntent(mContext, data.getId());
            }
        });

        if(spuSupplyType.equals("1")){
            helper.setText(R.id.tv_spuSupplyType,"自营");
        }else if(spuSupplyType.equals("2")){
            helper.setText(R.id.tv_spuSupplyType,"供应商");
        }

    }

    public GoodsSkusAdapter(List<ShopInfoData.SkusBean> data,String spuSupplyType) {
        super(R.layout.item_shop_skus, data);
        this.spuSupplyType = spuSupplyType;
    }
}
