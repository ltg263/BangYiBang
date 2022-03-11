package com.jxxx.byb.view.activity.login;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.byb.R;
import com.jxxx.byb.api.HttpsUtils;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.bean.LoginRequest;
import com.jxxx.byb.utils.StringUtil;
import com.jxxx.byb.utils.ToastUtil;
import com.jxxx.byb.view.activity.mine.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_verify)
    EditText etVerify;
    @BindView(R.id.auth_code)
    TextView authCode;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.cb)
    TextView cb;
    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override
    public int intiLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "注册");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.auth_code, R.id.tv_register, R.id.tv_forget, R.id.tv_login,R.id.ll_yhxy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.auth_code:
                String account = etAccount.getText().toString();
                if (StringUtil.isBlank(account)) {
                    ToastUtil.showLongStrToast(this, "请输入手机号");
                    return;
                }
                HttpsUtils.getVerifyCode(this,authCode,account,"register");
                break;
            case R.id.tv_register:
                toRegister();
                break;
            case R.id.tv_forget:
                baseStartActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tv_login:
                baseStartActivity(LoginEmsSendActivity.class);
                break;
            case R.id.ll_yhxy:
                baseStartActivity(WebViewActivity.class);
                break;
        }
    }

    private void toRegister() {
        String account = etAccount.getText().toString();
        String verify = etVerify.getText().toString();
        String pass = etPass.getText().toString();
        if (StringUtil.isBlank(account) || StringUtil.isBlank(verify)|| StringUtil.isBlank(pass)) {
            ToastUtil.showLongStrToast(this, "请输入完整信息");
            return;
        }
        LoginRequest bean = new LoginRequest();
        bean.setMobile(etAccount.getText().toString());
        bean.setPassword(pass);
        RetrofitUtil.getInstance().apiService()
                .smsRegister(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        hideLoading();
                        if(isResultOk(result)) {
                            ToastUtils.showLong("注册成功");
                            baseStartActivity(LoginEmsSendActivity.class,null);
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
}
