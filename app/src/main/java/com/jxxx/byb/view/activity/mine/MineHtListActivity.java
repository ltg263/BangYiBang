package com.jxxx.byb.view.activity.mine;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.byb.R;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.view.adapter.HomeOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MineHtListActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private HomeOrderAdapter mMineListHtAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_refresh_list;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "我的合同");
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mMineListHtAdapter = new HomeOrderAdapter(null);
        mRvList.setAdapter(mMineListHtAdapter);

        mMineListHtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        mMineListHtAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.bnt_1:
                        break;
                    case R.id.bnt_2:
                        break;
                    case R.id.bnt_3:
                        break;

                }
            }
        });
    }
}
