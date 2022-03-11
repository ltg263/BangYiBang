package com.jxxx.byb.view.fragment;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.byb.R;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseFragment;
import com.jxxx.byb.bean.ArticleCateBean;
import com.jxxx.byb.bean.ArticleCateListBean;
import com.jxxx.byb.bean.IndexGetBannerBean;
import com.jxxx.byb.utils.GlideImageLoader;
import com.jxxx.byb.utils.MagicIndicatorUtils;
import com.jxxx.byb.utils.StringUtil;
import com.jxxx.byb.view.adapter.HomeFourAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeFourListFragment extends BaseFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    int page = 1;
    private HomeFourAdapter mHomeFourAdapter;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_four_list;
    }

    @Override
    protected void initView() {
        mRvList.setHasFixedSize(true);
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getArticleList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                initData();
            }
        });
        mHomeFourAdapter = new HomeFourAdapter(null);
        mRvList.setAdapter(mHomeFourAdapter);
    }

    @Override
    protected void initData() {
        showLoading();
        RetrofitUtil.getInstance().apiService()
                .getIndexGetBanner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<IndexGetBannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<IndexGetBannerBean>> result) {
                        hideLoading();
                        if(isResultOk(result)){
                            if(result.getData()!=null){
                                ArrayList<String> list_path = new ArrayList<>();
                                for(int i=0;i<result.getData().size();i++){
                                    list_path.add(ConstValues.BASE_URL+result.getData().get(i).getImage());
                                }
                                bannerConfig(list_path);
                            }
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
        getArticleList();
    }

    private void getArticleList() {
        RetrofitUtil.getInstance().apiService()
                .getArticleList(getArguments().getString("cate_id"),page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ArticleCateListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ArticleCateListBean> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null){
                            if(page==1){
                                mHomeFourAdapter.setNewData(result.getData().getList());
                            }else{
                                mHomeFourAdapter.addData(result.getData().getList());
                            };
                            int totalPage = StringUtil.getTotalPage(result.getData().getCount(), ConstValues.PAGE_SIZE);
                            if(totalPage <= page){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        hideLoading();
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
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



