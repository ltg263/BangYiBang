package com.jxxx.byb.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.byb.MainActivity;
import com.jxxx.byb.R;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.base.BaseFragment;
import com.jxxx.byb.bean.DedicatedReceiptInfoBean;
import com.jxxx.byb.bean.PostOrderSubmit;
import com.jxxx.byb.utils.AddressPickTask;
import com.jxxx.byb.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineInvoice2Fragment extends BaseFragment {
    @BindView(R.id.et_receiptTitle)
    EditText mEtReceiptTitle;
    @BindView(R.id.et_ratepayerNo)
    EditText mEtRatepayerNo;
    @BindView(R.id.tv_receiptContent)
    TextView mTvReceiptContent;
    @BindView(R.id.tv_receiptAmount)
    TextView mTvReceiptAmount;
    @BindView(R.id.et_bank)
    EditText mEtBank;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.et_bankPhone)
    EditText mEtBankPhone;
    @BindView(R.id.et_contact)
    EditText mEtContact;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_region)
    TextView mTvRegion;
    @BindView(R.id.et_detailsAddress)
    EditText mEtDetailsAddress;

    String[] innerOrderNos;
    String typeS;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_mine_invoic_2;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        Log.w("bundle","bundle"+bundle);
        if(bundle!=null){
            innerOrderNos = bundle.getStringArray("innerOrderNos");
            mTvReceiptAmount.setText(bundle.getString("receiptAmount"));
            typeS = bundle.getString("type");
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_region, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_region:
                onAddressPicker();
                break;
            case R.id.tv_register:
                postReceiptApply();
                break;
        }
    }

    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask((Activity) mContext);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {

            @Override
            public void onAddressInitFailed() {
                ToastUtils.showShort("?????????????????????");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    mTvRegion.setText(province.getAreaName() +","+ city.getAreaName());
//                    addressData.setLandmark(province.getAreaName() +","+ city.getAreaName());
//                    addressData.setProvinceId(province.getAreaId());
//                    addressData.setCityId(city.getAreaId());

                } else {
                    mTvRegion.setText(province.getAreaName() +","+ city.getAreaName() +","+ county.getAreaName());
//                    addressData.setLandmark(province.getAreaName() +","+ city.getAreaName() +","+ county.getAreaName());
//                    addressData.setProvinceId(province.getAreaId());
//                    addressData.setCityId(city.getAreaId());
//                    addressData.setDistrictId(county.getAreaId());
                }
            }
        });
        task.execute("??????", "??????", "??????");
    }

    private void postReceiptApply(){
        String receiptTitle = mEtReceiptTitle.getText().toString();
        String ratepayerNo = mEtRatepayerNo.getText().toString();
        String receiptContent = mTvReceiptContent.getText().toString();
        String receiptAmount = mTvReceiptAmount.getText().toString();
        String bank = mEtBank.getText().toString();
        String account = mEtAccount.getText().toString();
        String address = mEtAddress.getText().toString();
        String phone = mEtPhone.getText().toString();
        String bankPhone = mEtBankPhone.getText().toString();
        String contact = mEtContact.getText().toString();
        String region = mTvRegion.getText().toString();
        String detailsAddress = mEtDetailsAddress.getText().toString();
        if(StringUtil.isBlank(receiptTitle)||
                StringUtil.isBlank(ratepayerNo)||
                StringUtil.isBlank(receiptContent)||
                StringUtil.isBlank(receiptAmount)||
                StringUtil.isBlank(bank)||
                StringUtil.isBlank(account)||
                StringUtil.isBlank(address)||
                StringUtil.isBlank(phone)||
                StringUtil.isBlank(bankPhone)||
                StringUtil.isBlank(contact)||
                StringUtil.isBlank(region)||
                StringUtil.isBlank(detailsAddress)){
            ToastUtils.showLong("?????????????????????");
            return;
        }

        if(StringUtil.isNotBlank(typeS) && typeS.equals("OrderAffirmActivity")){
            DedicatedReceiptInfoBean mDedicatedReceiptInfoBean = new DedicatedReceiptInfoBean(account, address, bank, phone, ratepayerNo, receiptAmount, receiptTitle, bankPhone, contact, detailsAddress, region);
            //Activity????????????????????????????????????????????????
            Intent data = new Intent();
            //?????????????????????????????????????????????
            data.putExtra("receiptType","2");
            data.putExtra("mDedicatedReceiptInfoBean", mDedicatedReceiptInfoBean);
            //??????Activity????????????data???????????????????????????????????????Activity?????????Activity
            ((Activity)mContext).setResult(1, data);
            //????????????Activity
            ((Activity)mContext).finish();
            return;
        }
        PostOrderSubmit.ReceiptApply mReceiptApply = new PostOrderSubmit.ReceiptApply();
        mReceiptApply.setInnerOrderNos(innerOrderNos);
        mReceiptApply.setReceiptType(2);
        mReceiptApply.setDedicatedReceiptInfo(
                new DedicatedReceiptInfoBean(account, address, bank, phone, ratepayerNo, receiptAmount, receiptTitle, bankPhone, contact, detailsAddress, region));
        Log.w("mReceiptApply","mReceiptApply:"+mReceiptApply.toString());
        RetrofitUtil.getInstance().apiService()
                .postReceiptApply(mReceiptApply)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==200) {
                            ToastUtils.showShort("????????????");
                            baseStartActivity(MainActivity.class,null);
                        }else{

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        dismiss();
                    }

                    @Override
                    public void onComplete() {
//                        dismiss();
                    }
                });
//
    }
}
