package com.jxxx.byb.view.activity;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.byb.R;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.bean.OrderHistoryDetailBean;
import com.jxxx.byb.utils.OrderListBntUtils;
import com.jxxx.byb.view.adapter.OrderShopAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderDetailsActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_shop_list)
    RecyclerView mRvShopList;
    @BindView(R.id.tv_orderStatus)
    TextView tv_orderStatus;
    @BindView(R.id.tv_totalAmount)
    TextView tv_totalAmount;
    @BindView(R.id.tv_freightAmount)
    TextView tv_freightAmount;
    @BindView(R.id.tv_discountAmount)
    TextView tv_discountAmount;
    @BindView(R.id.tv_couponAmount)
    TextView tv_couponAmount;
    @BindView(R.id.tv_payAmount)
    TextView tv_payAmount;
    @BindView(R.id.tv_deliveryAddress)
    TextView tv_deliveryAddress;
    @BindView(R.id.tv_innerOrderNo)
    TextView tv_innerOrderNo;
    @BindView(R.id.tv_placeTime)
    TextView tv_placeTime;
    @BindView(R.id.tv_payChannel)
    TextView tv_payChannel;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.tv_receiptType)
    TextView tv_receiptType;
    @BindView(R.id.bnt_1)
    TextView bnt_1;
    @BindView(R.id.bnt_2)
    TextView bnt_2;
    @BindView(R.id.bnt_3)
    TextView bnt_3;
    OrderShopAdapter mOrderShopAdapter;
    OrderHistoryDetailBean mData;
    @Override
    public int intiLayout() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "????????????");
        mOrderShopAdapter = new OrderShopAdapter(null);
        mRvShopList.setAdapter(mOrderShopAdapter);
    }

    @Override
    public void initData() {
        RetrofitUtil.getInstance().apiService()
                .getOrderHistoryDetail(getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrderHistoryDetailBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OrderHistoryDetailBean> result) {
                        if (isResultOk(result)) {
                            OrderDetailsActivity.this.mData = result.getData();
                            initUI();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void initUI() {
        bnt_1.setVisibility(View.INVISIBLE);
        bnt_2.setVisibility(View.INVISIBLE);
        bnt_3.setVisibility(View.INVISIBLE);
        String orderStatusString = "???";
        switch (mData.getOrderStatus()) {
            case "NONE":
                orderStatusString = "???";
                break;
            case "INIT":
                orderStatusString = "?????????";
                break;
            case "UNPAID":
                bnt_1.setVisibility(View.INVISIBLE);
                bnt_2.setVisibility(View.VISIBLE);
                bnt_3.setVisibility(View.VISIBLE);
                bnt_1.setText("");
                bnt_2.setText("????????????");
                bnt_3.setText("?????????");
                tv_state.setText("???????????????????????????????????????");
                orderStatusString = "?????????";
                break;
            case "UN_DELIVERY":
                bnt_1.setVisibility(View.INVISIBLE);
                bnt_2.setVisibility(View.VISIBLE);
                bnt_3.setVisibility(View.VISIBLE);
                bnt_1.setText("");
                bnt_2.setText("????????????");
                bnt_3.setText("????????????");
                tv_state.setText("???????????????????????????????????????");
                orderStatusString = "?????????";
                break;
            case "UN_RECEIVE":
                bnt_1.setVisibility(View.INVISIBLE);
                bnt_2.setVisibility(View.VISIBLE);
                bnt_3.setVisibility(View.VISIBLE);
                bnt_1.setText("");
                bnt_2.setText("????????????");
                bnt_3.setText("????????????");
                tv_state.setText("???????????????????????????????????????");
                orderStatusString = "?????????";
                break;
            case "FINISHED":
                bnt_1.setVisibility(View.INVISIBLE);
                bnt_2.setVisibility(View.INVISIBLE);
                bnt_3.setVisibility(View.VISIBLE);
                bnt_1.setText("");
                bnt_2.setText("");
                bnt_3.setText("????????????");
                tv_state.setText("???????????????????????????????????????");
                orderStatusString = "?????????";
                break;
            case "CANCELLED":
                bnt_1.setVisibility(View.INVISIBLE);
                bnt_2.setVisibility(View.INVISIBLE);
                bnt_3.setVisibility(View.VISIBLE);
                bnt_1.setText("");
                bnt_2.setText("");
                bnt_3.setText("????????????");
                tv_state.setText(mData.getCancelDesc());
                orderStatusString = "?????????";
                break;
            case "WAITING_REFUND":
                bnt_1.setVisibility(View.INVISIBLE);
                bnt_2.setVisibility(View.INVISIBLE);
                bnt_3.setVisibility(View.VISIBLE);
                bnt_1.setText("");
                bnt_2.setText("");
                bnt_3.setText("????????????");
                tv_state.setText("??????????????????");
                orderStatusString = "???????????????";
                break;
            case "REFUNDED":
                bnt_1.setVisibility(View.INVISIBLE);
                bnt_2.setVisibility(View.INVISIBLE);
                bnt_3.setVisibility(View.VISIBLE);
                bnt_1.setText("");
                bnt_2.setText("");
                bnt_3.setText("????????????");
                tv_state.setText("??????????????????????????????????????????????????????");
                orderStatusString = "?????????";
                break;
        }
        tv_orderStatus.setText(orderStatusString);
        mOrderShopAdapter.setNewData(mData.getOrderDetailList());
        tv_totalAmount.setText("???" + mData.getTotalAmount());
        tv_freightAmount.setText("-???" + mData.getFreightAmount());
        tv_discountAmount.setText("-???" + mData.getDiscountAmount());
        tv_couponAmount.setText("-???" + mData.getCouponAmount());
        tv_payAmount.setText("???" + mData.getPayAmount());
        tv_deliveryAddress.setText(mData.getDeliveryName() + "   " + mData.getDeliveryMobile() + "\n" + mData.getDeliveryAddress());
        tv_innerOrderNo.setText(mData.getInnerOrderNo());
        tv_placeTime.setText(mData.getPlaceTime());
        tv_payChannel.setText(mData.getPayChannel());
        switch (mData.getReceiptType()){
            case "0":
                tv_receiptType.setText("?????????");
                break;
            case "1":
                tv_receiptType.setText("??????????????????");
                break;
            case "2":
                tv_receiptType.setText("????????????");
                break;
        }
    }

    @OnClick({R.id.bnt_1, R.id.bnt_2, R.id.bnt_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_1:
            case R.id.bnt_2:
            case R.id.bnt_3:
                OrderListBntUtils.startOrderType(this, ((TextView) view).getText().toString(), mData,
                        new OrderListBntUtils.ShoppingCartInterface() {
                    @Override
                    public void isResult(Boolean isResult, String num) {
                        if(isResult){
                            initData();
                        }
                    }
                });
                break;
        }
    }
}




