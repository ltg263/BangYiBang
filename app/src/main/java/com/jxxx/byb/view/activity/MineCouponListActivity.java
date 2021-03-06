package com.jxxx.byb.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxxx.byb.R;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.utils.MagicIndicatorUtils;
import com.jxxx.byb.view.fragment.MineCouponFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MineCouponListActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.bnt)
    TextView bnt;

    private final String[] CHANNELS = new String[]{"未使用", "已使用", "已过期"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    @Override
    public int intiLayout() {
        return R.layout.activity_toolbar_indicator_viewpager;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "我的优惠券");
        MagicIndicatorUtils.initMagicIndicator_1(this, mDataList, mMagicIndicator, mViewPager);
        initVP();
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseStartActivity(OrderCouponListActivity.class,null);
            }
        });
    }
    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(CHANNELS.length);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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

    //1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        for(int i = 0;i<CHANNELS.length;i++){
            MineCouponFragment mMineCouponFragment = new MineCouponFragment();
            Bundle mBundle = new Bundle();
            mBundle.putString("status",i+"");
            mMineCouponFragment.setArguments(mBundle);
            fragments.add(mMineCouponFragment);
        }
        return fragments;
    }
    @Override
    public void initData() {

    }

}
