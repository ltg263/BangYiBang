package com.jxxx.byb.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxxx.byb.R;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.base.BaseFragment;
import com.jxxx.byb.bean.ArticleCateBean;
import com.jxxx.byb.utils.MagicIndicatorUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFourFragment extends BaseFragment {

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private List<String> mDataList = new ArrayList<>();
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_four;
    }

    @Override
    protected void initView() {
        RetrofitUtil.getInstance().apiService()
                .getArticleCate()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<ArticleCateBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<ArticleCateBean>> result) {
                        hideLoading();
                        if(isResultOk(result) && result.getData()!=null){
                            for(int i=0;i<result.getData().size();i++){
                                mDataList.add(result.getData().get(i).getName());
                            }
                            MagicIndicatorUtils.initMagicIndicator_2(mContext,false,mDataList, mMagicIndicator, mViewPager);
                            initVP(result.getData());
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

    @Override
    protected void initData() {

    }
    private void initVP(List<ArticleCateBean> data) {
        getFragments(data);
        mViewPager.setOffscreenPageLimit(data.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });
        mViewPager.setCurrentItem(0);
    }
    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments(List<ArticleCateBean> data) {
        for(int i = 0;i<mDataList.size();i++){
            Bundle mBundle1 = new Bundle();
            mBundle1.putString("cate_id",data.get(i).getId());
            HomeFourListFragment mHomeOrderListFragment = new HomeFourListFragment();
            mHomeOrderListFragment.setArguments(mBundle1);
            fragments.add(mHomeOrderListFragment);
        }
        return fragments;
    }
}



