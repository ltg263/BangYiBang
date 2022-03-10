package com.jxxx.byb.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class IntentUtils {
    public static IntentUtils instence;

    public static IntentUtils getInstence() {
        if (null == instence) {
            instence = new IntentUtils();
        }
        return instence;
    }

    private IntentUtils() {

    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public static void startActivityPhone(Context mContext, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        mContext.startActivity(intent);
    }

    public void intent(Context fromContext, Class<?> cls) {
        Intent intent = new Intent(fromContext, cls);
        fromContext.startActivity(intent);
    }
    public void intent(Context fromContext, Class<?> cls, String name, Bundle con) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name, con);
        fromContext.startActivity(intent);
    }

    public void intent(Context fromContext, Class<?> cls, String name, String con) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        fromContext.startActivity(intent);
    }
}

