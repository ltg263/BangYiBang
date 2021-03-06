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
            mTvGys.setText("自营");
        } else if (data.getSpuSupplyType().equals("2")) {
            mTvGys.setText("供应商");
        }
        mTvName.setText(data.getSpuName());
        mTvPrice.setText("无价格");
        if (data.getPriceInfo() != null) {
            String str = "￥<big><big>"+data.getPriceInfo().getPrice()+"</big></big></font>" +data.getPriceInfo().getUnit();
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
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        mBanner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        mBanner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
//        banner_theme.setBannerTitles(themeTitles);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        mBanner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    private void initWebView(String details) {
        WebSettings webSettings = mWebView.getSettings();//获取webview设置属性
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webSettings.setBlockNetworkImage(false); // 解决图片不显示
        //支持javascript
//        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.loadData(
                StringUtil.getNewContent1(details), "text/html; charset=UTF-8", null);//这种写法可以正确解码
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
                ConstValues.SHOW_MAIN_FRAGMENT = "购物车";
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
