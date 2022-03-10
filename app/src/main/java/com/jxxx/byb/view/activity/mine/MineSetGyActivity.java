package com.jxxx.byb.view.activity.mine;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.byb.R;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.utils.DialogHelper;

import butterknife.BindView;

public class MineSetGyActivity extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.tv_dqbb)
    TextView tv_dqbb;

    @Override
    public int intiLayout() {
        return R.layout.activity_mine_set_gy;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "关于我们");
        tv_dqbb.setText("V"+ DialogHelper.getVersionName(this));
    }

    @Override
    public void initData() {

    }
}
