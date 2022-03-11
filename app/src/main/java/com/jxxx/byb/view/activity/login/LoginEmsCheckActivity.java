package com.jxxx.byb.view.activity.login;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.byb.MainActivity;
import com.jxxx.byb.R;
import com.jxxx.byb.api.HttpsUtils;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.BaseActivity;
import com.jxxx.byb.bean.LoginData;
import com.jxxx.byb.bean.LoginRequest;
import com.jxxx.byb.utils.IntentUtils;
import com.jxxx.byb.utils.SharedUtils;
import com.jxxx.byb.utils.view.NumberEditText;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginEmsCheckActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mMyToolbar;
    @BindView(R.id.tv_ems_mobile)
    TextView mTvEmsMobile;
    @BindView(R.id.net_check)
    NumberEditText mNetCheck;
    @BindView(R.id.tv_ems)
    TextView mTvEms;
    String mobile;
    @Override
    public int intiLayout() {
        return R.layout.activity_login_ems_check;
    }

    @Override
    public void initView() {
        setToolbar(mMyToolbar, "");
        mobile = getIntent().getStringExtra("mobile");
        mTvEmsMobile.setText("发送验证码到"+mobile);
        mNetCheck.setOnInputFinish(new NumberEditText.OnInputFinishListener() {
            @Override
            public void onInputFinish(String captcha) {
                userMobileLogin(captcha);
            }
        });
        mTvEms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });
    }

    private void userMobileLogin(String captcha) {
        LoginRequest bean = new LoginRequest();
        bean.setMobile(mobile);
        bean.setCaptcha(captcha);//1注册 2登录 3找回密码
        RetrofitUtil.getInstance().apiService()
                .postUserMobileLogin(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LoginData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LoginData> result) {

                        if(isResultOk(result)) {
                            LoginData.UserinfoBean userinfo = result.getData().getUserinfo();
                            ConstValues.ISLOGIN = true;
                            SharedUtils.singleton().put(ConstValues.TOKENID,userinfo.getToken());
                            IntentUtils.getInstence().intent(LoginEmsCheckActivity.this, MainActivity.class);
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

    @Override
    public void initData() {
        HttpsUtils.getVerifyCode(this,mTvEms,mobile,"mobilelogin");
    }
}
