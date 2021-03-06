package com.jxxx.byb.view.activity.login;

import android.text.TextUtils;
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

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForgetPasswordActivity extends BaseActivity {


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
    @BindView(R.id.tv_done)
    TextView tvDone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password2)
    EditText etPassword2;

    @Override
    public int intiLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "修改密码");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.auth_code, R.id.tv_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.auth_code:
                String account = etAccount.getText().toString();
                if (StringUtil.isBlank(account)) {
                    ToastUtil.showLongStrToast(this, "请输入手机号");
                    return;
                }
                HttpsUtils.getVerifyCode(this,authCode,account,"resetpwd");
                break;
            case R.id.tv_done:
                String pw1 = etPassword.getText().toString().trim();
                String pw2 = etPassword2.getText().toString().trim();
                String code = etVerify.getText().toString().trim();
                String phone = etAccount.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pw1) || TextUtils.isEmpty(pw2) || TextUtils.isEmpty(code)) {
                    ToastUtils.showShort("请输入完整内容");
                    return;
                }
                if (!pw1.equals(pw2)) {
                    ToastUtils.showShort("两次密码输入不一致！");
                    return;
                }
                LoginRequest bean = new LoginRequest();
                bean.setMobile(phone);
                bean.setNewpassword(pw2);
                bean.setCaptcha(code);
                showLoading();
                RetrofitUtil.getInstance().apiService()
                        .postSmsFindPwd(bean)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<Result>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Result result) {
                                hideLoading();
                                if(isResultOk(result)){
                                    ToastUtils.showShort("修改成功");
                                    finish();
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
                break;
        }
    }
}
