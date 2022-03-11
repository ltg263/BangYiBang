package com.jxxx.byb.app;

/**
 * Created by Administrator on 2017/9/1.
 */

public class ConstValues {
    public static String SHOW_MAIN_FRAGMENT = "show_main_fragment";
    /**
     * 应用名称
     */
    public static String APPNAME_ENGLISH = "BangYiBang";
    public static String CATEGORY = "";

    /**sharedpreference 判断是否已登录字段*/
    public static boolean ISLOGIN = false;
    public static final String USERID = "user_id";
    public static final String[] ORDER_CANCEL = {"拍多了，拍错了","商家营业，但不接待","商家关店、装修、转让","联系不上商家，或实地地址无此店"};
    public static final String[] ORDER_REFUND = {"买多了，买错了","商家营业，但不接待","商家关店、装修、转让","联系不上商家，或实地地址无此店"};

    public static final String endpoint = "endpoint";
    public static final String host = "host";
    public static final String TOKENID = "tokenid";

    /**
     * 服务器后台地址
     */
    public static final String BASE_URL = "http://api.byb985.com/";


    //默认连接超时时间
    public static final int DEFAULT_TIMEOUT =60;
    public static final int PAGE_SIZE =10;

    /**
     * 菜牛进货用户协议
     */
    public static final String USER_XY_URL = "http://www.cainiu666.com/jinhuoxieyi.html";
    /**
     * 菜牛进货隐私政策
     */
    public static final String USER_YSZC_URL = "https://www.cainiu666.com/yinsi.html";
    /**
     * 售后规则
     */
    public static final String USER_SHGZ_URL = "http://www.cainiu666.com/shouhouxieyi.html";
    /**
     *  常见问题
     */
    public static final String USER_CJWT_URL = "http://www.cainiu666.com/changjianwenti.html";
}
