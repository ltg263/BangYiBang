package com.jxxx.byb.view.fragment;


import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.byb.R;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseFragment;
import com.jxxx.byb.utils.GlideImageLoader;
import com.jxxx.byb.view.activity.login.LoginEmsSendActivity;
import com.jxxx.byb.view.activity.search.SearchGoodsActivity;
import com.jxxx.byb.view.adapter.HomeFourAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeFourListFragment extends BaseFragment {

    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private HomeFourAdapter mHomeFourAdapter;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_four_list;
    }

    @Override
    protected void initView() {
        mRvList.setHasFixedSize(true);
        mHomeFourAdapter = new HomeFourAdapter(null);
        mRvList.setAdapter(mHomeFourAdapter);
    }

    @Override
    protected void initData() {
        RetrofitUtil.getInstance().apiService()
                .getIndexIndex()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        hideLoading();
                        if(isResultOk(result)){
//                            if(result.getData()!=null){
                                ArrayList<String> list_path = new ArrayList<>();
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                list_path.add("https://scmp-oss.cainiu666.com/1644365420012.jpg");
                                bannerConfig(list_path);
                            mHomeFourAdapter.setNewData(list_path);
//                            }
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

    private void bannerConfig(List<String> list_path) {

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        mHomeBanner.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        mHomeBanner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        mHomeBanner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
//        banner_theme.setBannerTitles(themeTitles);
        //设置轮播间隔时间
        mHomeBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        mHomeBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
    }
}



