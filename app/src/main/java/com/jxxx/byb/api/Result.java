package com.jxxx.byb.api;

import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.jxxx.byb.app.MainApplication;

/**
 * 公共返回格式
 *
 * @param <T>
 */
public class Result<T> {


    private int code;
    private long time;
    private String msg;
    private T data;

    public int getCode() {
        if(code!=1){
            Toast.makeText(MainApplication.getContext(),msg,Toast.LENGTH_LONG).show();
        }
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
