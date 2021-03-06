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

        //???????????????????????????????????????????????????????????????????????????
        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //????????????????????????????????????????????????
        mHomeBanner.setImageLoader(new GlideImageLoader());
        //????????????????????????????????????
        mHomeBanner.setImages(list_path);
        //?????????????????????????????????????????????????????????????????????????????????????????????
        mHomeBanner.setBannerAnimation(Transformer.Default);
        //??????????????????????????????
//        banner_theme.setBannerTitles(themeTitles);
        //????????????????????????
        mHomeBanner.setDelayTime(3000);
        //???????????????????????????????????????????????????
        mHomeBanner.isAutoPlay(true);
        //???????????????????????????????????????????????????
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER)
                //???????????????????????????????????????????????????????????????????????????????????????????????????
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                    }
                })
                //????????????????????????????????????????????????
                .start();
    }
}



