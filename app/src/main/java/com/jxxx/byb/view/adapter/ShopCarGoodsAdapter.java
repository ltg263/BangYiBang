package com.jxxx.byb.view.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.byb.MainActivity;
import com.jxxx.byb.R;
import com.jxxx.byb.api.HttpsUtils;
import com.jxxx.byb.bean.ShoppingCartListBean;
import com.jxxx.byb.utils.GlideImageLoader;
import com.jxxx.byb.utils.view.AddandView;

import java.util.List;

public class ShopCarGoodsAdapter extends BaseQuickAdapter<ShoppingCartListBean.ItemListBean, BaseViewHolder> {

    public ShopCarGoodsAdapter(List<ShoppingCartListBean.ItemListBean> data) {
        super(R.layout.item_shop_car_goodes, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCartListBean.ItemListBean item) {
        GlideImageLoader.loadImageViewRadius(mContext,item.getCartSpuDTO().getIconUrl(),30,helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_name,item.getCartSpuDTO().getSpuName())
                .setVisible(R.id.iv_add,false).setVisible(R.id.tv_add,false).setVisible(R.id.add,true)
                .addOnClickListener(R.id.iv_select)
                .setText(R.id.tv_type,item.getCartSpuDTO().getCartSkuDTO().getSkuName())
                .setText(R.id.tv_price,item.getCartSpuDTO().getCartSkuDTO().getSkuPriceDTO().getSkuPrice());
        AddandView mAddandView = helper.getView(R.id.add);
        ImageView mIvSelect = helper.getView(R.id.iv_select);
        mIvSelect.setSelected(item.getChecked().equals("1")?true:false);
        mAddandView.setValue(Integer.valueOf(item.getCartSpuDTO().getCartSkuDTO().getSkuNum()));
        if(item.getCartSpuDTO().getSpuSupplyType().equals("1")){
            helper.setText(R.id.tv_spuSupplyType,"自营");
        }else if(item.getCartSpuDTO().getSpuSupplyType().equals("2")){
            helper.setText(R.id.tv_spuSupplyType,"供应商");
        }

        mAddandView.setOnNumberChangedListener(new AddandView.OnNumberChangedListener() {
            @Override
            public void OnNumberChanged(int vs, boolean isAdd) {
                if(isAdd){
                    HttpsUtils.userRechargeOrder(mContext, item.getCartSpuDTO().getCartSkuDTO().getId(),
                            item.getCartSpuDTO().getId(), new HttpsUtils.ShoppingCartInterface() {
                        @Override
                        public void isResult(Boolean isResult,String num) {
                            if(isResult){
                                ((MainActivity)mContext).updateUI();
                                if(mContext instanceof MainActivity){
                                    ((MainActivity)mContext).setShopCarNum(num);
                                }
                                mAddandView.add();
                            }
                        }
                    });
                }else{
                    HttpsUtils.shoppingCartReduce(mContext, item.getCartSpuDTO().getCartSkuDTO().getId()
                            , item.getCartSpuDTO().getId(), new HttpsUtils.ShoppingCartInterface() {
                                @Override
                                public void isResult(Boolean isResult,String num) {
                                    if(isResult){
                                        ((MainActivity)mContext).updateUI();
                                        if(mContext instanceof MainActivity){
                                            ((MainActivity)mContext).setShopCarNum(num);
                                        }
                                        mAddandView.jian();
                                    }
                                }
                            });
                }
            }
        });
    }

}
