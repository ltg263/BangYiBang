package com.jxxx.byb.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.byb.MainActivity;
import com.jxxx.byb.R;
import com.jxxx.byb.api.HttpsUtils;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.base.ShopInfoData;
import com.jxxx.byb.base.ShopInfoListData;
import com.jxxx.byb.utils.GlideImageLoader;
import com.jxxx.byb.utils.StatusBarUtil;
import com.jxxx.byb.utils.StringUtil;
import com.jxxx.byb.utils.view.SquareRelativeLayout;
import com.jxxx.byb.view.activity.login.LoginEmsSendActivity;
import com.jxxx.byb.view.adapter.HomeGoodsGzAdapter;
import com.jxxx.byb.view.adapter.HomeGoodsTyAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopDetailsActivity extends BaseActivity {
    @BindView(R.id.rv_xgsp)
    RecyclerView mRvXgsp;
    @BindView(R.id.home_banner)
    Banner mBanner;
    @BindView(R.id.rv_gui_ze)
    RecyclerView mRvGuiZe;
    @BindView(R.id.rv_tjsp)
    RecyclerView mRvTjsp;
    @BindView(R.id.web)
    WebView mWebView;
    @BindView(R.id.bnt_fh)
    ImageView mBntFh;
    @BindView(R.id.bnt_fx)
    ImageView mBntFx;
    @BindView(R.id.srl)
    SquareRelativeLayout mSrl;
    @BindView(R.id.tv_gys)
    TextView mTvGys;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.tv_spuShortDesc)
    TextView tv_spuShortDesc;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.btn_brcm)
    TextView mBtnBrcm;
    @BindView(R.id.btn_gwc)
    TextView mBtnGwc;
    @BindView(R.id.btn_ljgm)
    TextView mBtnLjgm;
    @BindView(R.id.ll_b)
    LinearLayout mLlB;
    String skuId = "";
    private HomeGoodsGzAdapter mHomeGoodsGzAdapter;

    @Override
    public int intiLayout() {
        StatusBarUtil.setTranslucentStatus(this);
        return R.layout.activity_shop_details;
    }

    @Override
    public void initView() {
        mHomeGoodsGzAdapter = new HomeGoodsGzAdapter(null);
        mRvGuiZe.setAdapter(mHomeGoodsGzAdapter);
        mHomeGoodsGzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                skuId = mHomeGoodsGzAdapter.getData().get(position).getId();
                mHomeGoodsGzAdapter.setCurPos(position);
                mHomeGoodsGzAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initData() {
        String url = "api/scmp-application-mall/product/detail/" + getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH);
        RetrofitUtil.getInstance().apiService()
                .productDetail(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ShopInfoListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ShopInfoListData> result) {
                        hideLoading();
                        if (isResultOk(result)) {
                            mRvTjsp.setAdapter(new HomeGoodsTyAdapter(result.getData().getRecommendList()));
                            mRvXgsp.setAdapter(new HomeGoodsTyAdapter(result.getData().getRelatedList()));
                            initDetail(result.getData().getSpu());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        hideLoading();
                    }
                });
    }

    private void initDetail(ShopInfoData data) {
        initWebView(data.getSpuDesc());
        bannerConfig(data.getSpuImgs());

        if (data.getSpuSupplyType().equals("1")) {
            mTvGys.setText("??????");
        } else if (data.getSpuSupplyType().equals("2")) {
            mTvGys.setText("?????????");
        }
        mTvName.setText(data.getSpuName());
        mTvPrice.setText("?????????");
        if (data.getPriceInfo() != null) {
            String str = "???<big><big>"+data.getPriceInfo().getPrice()+"</big></big></font>" +data.getPriceInfo().getUnit();
            mTvPrice.setText(Html.fromHtml(str));
        }
        tv_spuShortDesc.setText(data.getSpuParams());
        skuId = data.getSkus().get(0).getId();
        mHomeGoodsGzAdapter.setNewData(data.getSkus());
    }

    private void bannerConfig(String[] supImgs) {
        ArrayList<String> list_path = new ArrayList<>();
        for (int i = 0; i < supImgs.length; i++) {
            list_path.add(supImgs[i]);
        }
        //???????????????????????????????????????????????????????????????????????????
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //????????????????????????????????????????????????
        mBanner.setImageLoader(new GlideImageLoader());
        //????????????????????????????????????
        mBanner.setImages(list_path);
        //?????????????????????????????????????????????????????????????????????????????????????????????
        mBanner.setBannerAnimation(Transformer.Default);
        //??????????????????????????????
//        banner_theme.setBannerTitles(themeTitles);
        //????????????????????????
        mBanner.setDelayTime(3000);
        //???????????????????????????????????????????????????
        mBanner.isAutoPlay(true);
        //???????????????????????????????????????????????????
        mBanner.setIndicatorGravity(BannerConfig.CENTER)
                //???????????????????????????????????????????????????????????????????????????????????????????????????
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                    }
                })
                //????????????????????????????????????????????????
                .start();
    }

    private void initWebView(String details) {
        WebSettings webSettings = mWebView.getSettings();//??????webview????????????
        webSettings.setDefaultTextEncodingName("UTF-8");//???????????????utf-8
        webSettings.setBlockNetworkImage(false); // ?????????????????????
        //??????javascript
//        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.loadData(
                StringUtil.getNewContent1(details), "text/html; charset=UTF-8", null);//??????????????????????????????
    }

    public static void startActivityIntent(Context mContext, String id) {
        Intent mIntent = new Intent(mContext, ShopDetailsActivity.class);
        mIntent.putExtra(ConstValues.APPNAME_ENGLISH, id);
        mContext.startActivity(mIntent);
    }

    @OnClick({R.id.btn_brcm, R.id.btn_gwc, R.id.btn_ljgm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_brcm:

                break;
            case R.id.btn_gwc:
                ConstValues.SHOW_MAIN_FRAGMENT = "?????????";
                baseStartActivity(MainActivity.class);
                break;
            case R.id.btn_ljgm:
                if(!ConstValues.ISLOGIN){
                    LoginEmsSendActivity.startActivityLogin(this);
                    return;
                }
                HttpsUtils.userRechargeOrder(this, skuId,
                        getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH), new HttpsUtils.ShoppingCartInterface() {
                    @Override
                    public void isResult(Boolean isResult,String num) {

                    }
                });
                break;
        }
    }
}
