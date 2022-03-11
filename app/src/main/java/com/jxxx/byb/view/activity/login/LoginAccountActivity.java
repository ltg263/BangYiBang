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
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.bean.LoginRequest;
import com.jxxx.byb.utils.IntentUtils;
import com.jxxx.byb.utils.StringUtil;
import com.jxxx.byb.utils.ToastUtil;
import com.jxxx.byb.view.activity.mine.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginAccountActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;

    public static void startActivityLogin(Context mContext) {
        mContext.startActivity(new Intent(mContext, LoginAccountActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_login_account;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "");
        StringUtil.etSearchChangedListener(mEtAccount, mTvLogin, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                if(StringUtil.isNotBlank(mEtAccount.getText().toString()) && StringUtil.isNotBlank(mEtPassword.getText().toString()) ){
                    mTvLogin.setClickable(true);
                    mTvLogin.setSelected(true);
                }else{
                    mTvLogin.setClickable(false);
                    mTvLogin.setSelected(false);
                }
            }
        });
        StringUtil.etSearchChangedListener(mEtPassword, mTvLogin, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                if(StringUtil.isNotBlank(mEtAccount.getText().toString()) && StringUtil.isNotBlank(mEtPassword.getText().toString()) ){
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


    @OnClick({R.id.tv_login, R.id.iv_select, R.id.tv_ems_login,R.id.tv_forget_pas,R.id.tv_info, R.id.tv_ysty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                if(!mIvSelect.isSelected()){
                    ToastUtil.showShortToast(LoginAccountActivity.this,"请先阅读并同意《用户协议》和《隐私政策》");
                    return;
                }
                String account = mEtAccount.getText().toString();
                String password = mEtPassword.getText().toString();
                userLogin(account,password);
                break;
            case R.id.iv_select:
                mIvSelect.setSelected(!mIvSelect.isSelected());
                break;
            case R.id.tv_ems_login:
                finish();
                break;
            case R.id.tv_forget_pas:
                IntentUtils.getInstence().intent(this,ForgetPasswordActivity.class);
                break;
            case R.id.tv_info:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_XY_URL,"用户协议");
                break;
            case R.id.tv_ysty:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_YSZC_URL,"隐私政策");
                break;
        }
    }

    private void userLogin(String account, String password) {
        LoginRequest bean = new LoginRequest();
        bean.setAccount(account);
        bean.setPassword(password);//1注
        RetrofitUtil.getInstance().apiService()
                .postUserLogin(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==1) {
                            ToastUtil.showShortToast(LoginAccountActivity.this,"登录成功");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
