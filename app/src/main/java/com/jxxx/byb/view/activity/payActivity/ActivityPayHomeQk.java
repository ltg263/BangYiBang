package com.jxxx.byb.view.activity.payActivity;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.byb.R;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseActivity;

import butterknife.BindView;

/**
 * 我的钱包
 */
public class ActivityPayHomeQk extends BaseActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.tv_owedAmount)
    TextView tv_owedAmount;

    @Override
    public int intiLayout() {
        return R.layout.activity_pay_home_pk;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "欠款");
    }

    @Override
    public void initData() {
        tv_owedAmount.setText(getIntent().getStringExtra(ConstValues.APPNAME_ENGLISH));
    }

}
