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

public class HomeFourFragment extends BaseFragment {

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private final String[] CHANNELS = new String[]{"推荐", "精彩资讯", "项目", "正能量文章"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_four;
    }

    @Override
    protected void initView() {
        MagicIndicatorUtils.initMagicIndicator_2(getActivity(), mDataList, mMagicIndicator, mViewPager);
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
            String orderStatusString = "null";
            mBundle1.putString("orderStatusString",orderStatusString);
            HomeFourZnlFragment mHomeOrderListFragment = new HomeFourZnlFragment();
            mHomeOrderListFragment.setArguments(mBundle1);
            fragments.add(mHomeOrderListFragment);
        }
        return fragments;
    }
}



