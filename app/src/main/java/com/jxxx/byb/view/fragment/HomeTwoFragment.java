package com.jxxx.byb.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.byb.R;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseFragment;
import com.jxxx.byb.base.CommodityCategory;
import com.jxxx.byb.base.HomeCategoryTypeData;
import com.jxxx.byb.utils.MagicIndicatorUtils;
import com.jxxx.byb.utils.StatusBarUtil;
import com.jxxx.byb.view.activity.login.LoginEmsSendActivity;
import com.jxxx.byb.view.activity.search.SearchGoodsActivity;
import com.jxxx.byb.view.adapter.HomeCategoryChildAdapter;
import com.jxxx.byb.view.adapter.HomeCategoryContentAdapter;
import com.jxxx.byb.view.adapter.HomeCategoryParentAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 1000D 订单管理
 */
public class HomeTwoFragment extends BaseFragment {

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private final String[] CHANNELS = new String[]{"待接单", "已接单"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_two;
    }

    @Override
    protected void initView() {
        MagicIndicatorUtils.initMagicIndicator_2(mContext,true, mDataList, mMagicIndicator, mViewPager);
        initVP();

    }
    @Override
    protected void initData() {

    }
    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(mDataList.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

//            @Nullable
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return "";
//            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                Log.w("onPageSelected","onPageScrollStateChanged:"+position);
                // 切换到当前页面，重置高度
                mViewPager.requestLayout();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setCurrentItem(0);
    }
    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        for(int i = 0;i<mDataList.size();i++){
            Bundle mBundle1 = new Bundle();
            String orderStatusString = "null";
            mBundle1.putString("orderStatusString",orderStatusString);
            HomeTwoListFragment mHomeTwoListFragment = new HomeTwoListFragment();
            mHomeTwoListFragment.setArguments(mBundle1);
            fragments.add(mHomeTwoListFragment);
        }
        return fragments;
    }
}