package com.jxxx.byb.view.activity.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.byb.R;
import com.jxxx.byb.api.HttpsUtils;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.utils.IntentUtils;
import com.jxxx.byb.utils.StringUtil;
import com.jxxx.byb.utils.ToastUtil;
import com.jxxx.byb.view.activity.mine.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginEmsSendActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;

    public static void startActivityLogin(Context mContext) {
        mContext.startActivity(new Intent(mContext, LoginEmsSendActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_login_ems_send;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "");
        StringUtil.etSearchChangedListener(mEtAccount, mTvLogin, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                if(mEtAccount.getText().toString().length()==11){
                    mTvLogin.setClickable(true);
                    mTvLogin.setSelected(true);
                }else{
                    mTvLogin.setClickable(false);
                    mTvLogin.setSelected(false);
                }
            }
        });
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tv_login, R.id.iv_select, R.id.tv_account_login,R.id.tv_info, R.id.tv_ysty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                if(!mIvSelect.isSelected()){
                    ToastUtil.showShortToast(LoginEmsSendActivity.this,"请先阅读并同意《用户协议》和《隐私政策》");
                    return;
                }
                IntentUtils.getInstence().intent(this,LoginEmsCheckActivity.class,"mobile",mEtAccount.getText().toString());
                break;
            case R.id.iv_select:
                mIvSelect.setSelected(!mIvSelect.isSelected());
                break;
            case R.id.tv_account_login:
                IntentUtils.getInstence().intent(this,LoginAccountActivity.class);
                break;
            case R.id.tv_info:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_XY_URL,"用户协议");
                break;
            case R.id.tv_ysty:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_YSZC_URL,"隐私政策");
                break;
        }
    }
}
