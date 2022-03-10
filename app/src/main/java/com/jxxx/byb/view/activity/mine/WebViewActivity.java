package com.jxxx.byb.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.widget.Toolbar;

import com.jxxx.byb.R;
import com.jxxx.byb.base.BaseActivity;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.web)
    WebView mWeb;


    @Override
    public int intiLayout() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        setWebViewClient();
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        setToolbar(myToolbar, title);
        setWebViewClient();
        mWeb.loadUrl(url);
    }

    @Override
    public void initData() {

    }

    public static void startActivityIntent(Context mContext, String url, String title) {
        Intent mIntent = new Intent(mContext, WebViewActivity.class);
        mIntent.putExtra("url", url);
        mIntent.putExtra("title", title);
        mContext.startActivity(mIntent);
    }

    private void setWebViewClient() {
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持js交互，可以点击网页中按钮链接
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js可以打开新的页面
        webSettings.setSupportZoom(true);//是否可以缩放，默认true
        webSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(false);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//DOM Storage


//        mWeb.setOnTouchScreenListener(new MyWebView.OnTouchScreenListener() {
//
//            @Override
//            public void onTouchScreen() {
//                Log.w("-->>","++++++");
////                isFlowing = true;
//                if (mRl.getVisibility() == View.GONE) {
//                    mRl.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onReleaseScreen() {
////                isFlowing = false;
//                Log.w("-->>","--->>");
//                if (mRl.getVisibility() == View.VISIBLE) {
//                    mRl.setVisibility(View.GONE);
//                }
//            }
//        });
    }

}