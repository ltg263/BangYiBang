package com.jxxx.byb.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxxx.byb.R;
import com.jxxx.byb.base.BaseFragment;
import com.jxxx.byb.utils.MagicIndicatorUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class HomeOrderFragment extends BaseFragment {

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private final String[] CHANNELS = new String[]{"全部", "待支付", "待发货", "待收货", "已收货", "已取消"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_four;
    }

    @Override
    protected void initView() {
        MagicIndicatorUtils.initMagicIndicator_1(getActivity(), mDataList, mMagicIndicator, mViewPager);
        initVP();

    }

    @Override
    protected void initData() {

    }
    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(CHANNELS.length);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
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
    private List<Fragment> getFragments() {
        for(int i = 0;i<mDataList.size();i++){
            Bundle mBundle1 = new Bundle();
            String orderStatusString = null;
            switch (i){
                case 0:
                    orderStatusString = null;
                    break;
                case 1:
                    orderStatusString = "UNPAID";
                    break;
                case 2:
                    orderStatusString = "UN_DELIVERY";
                    break;
                case 3:
                    orderStatusString = "UN_RECEIVE";
                    break;
                case 4:
                    orderStatusString = "FINISHED";
                    break;
                case 5:
                    orderStatusString = "CANCELLED";
                    break;
            }
            mBundle1.putString("orderStatusString",orderStatusString);
            HomeOrderListFragment mHomeOrderListFragment = new HomeOrderListFragment();
            mHomeOrderListFragment.setArguments(mBundle1);
            fragments.add(mHomeOrderListFragment);

        }
        return fragments;
    }
}



