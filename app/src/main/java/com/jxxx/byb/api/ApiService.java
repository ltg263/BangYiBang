package com.jxxx.byb.api;


import com.jxxx.byb.app.ConstValues;
import com.jxxx.byb.base.CommodityCategory;
import com.jxxx.byb.base.HomeCategoryTypeData;
import com.jxxx.byb.base.ShopInfoData;
import com.jxxx.byb.base.ShopInfoListData;
import com.jxxx.byb.bean.AccoutInfoBean;
import com.jxxx.byb.bean.AddressModel;
import com.jxxx.byb.bean.ArticleCateBean;
import com.jxxx.byb.bean.ArticleCateListBean;
import com.jxxx.byb.bean.CategoryDataList;
import com.jxxx.byb.bean.CategoryTreeData;
import com.jxxx.byb.bean.ChannelsListBean;
import com.jxxx.byb.bean.CouponTemplateData;
import com.jxxx.byb.bean.GlobalAdconfigBean;
import com.jxxx.byb.bean.HomeActivityData;
import com.jxxx.byb.bean.HomeCategoryData;
import com.jxxx.byb.bean.IndexGetBannerBean;
import com.jxxx.byb.bean.LoginData;
import com.jxxx.byb.bean.LoginRequest;
import com.jxxx.byb.bean.OrderHistoryBean;
import com.jxxx.byb.bean.OrderHistoryDetailBean;
import com.jxxx.byb.bean.OrderInfoBean;
import com.jxxx.byb.bean.OrderPreviewBean;
import com.jxxx.byb.bean.OrderRefundHistoryBean;
import com.jxxx.byb.bean.OrderSubmitData;
import com.jxxx.byb.bean.ParamData;
import com.jxxx.byb.bean.PayDataBean;
import com.jxxx.byb.bean.PostAuditSubmitCommand;
import com.jxxx.byb.bean.PostOrderSubmit;
import com.jxxx.byb.bean.ProductListBean;
import com.jxxx.byb.bean.RechargeAllBean;
import com.jxxx.byb.bean.ShoppingCartListBean;
import com.jxxx.byb.bean.SubmitFilesBean;
import com.jxxx.byb.bean.UserInfoUpdate;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    /**
     * 全局-发送短信验证码
     * @param request
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/sms/send")
    Observable<Result> postSendSms(@Body LoginRequest request);

    /**
     * 登录页-手机短验登录接口
     * @param request
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/user/mobilelogin")
    Observable<Result<LoginData>> postUserMobileLogin(@Body LoginRequest request);

    /**
     * 登录页-手机密码登录接口
     * @param request
     * @return
     */
    @POST(ConstValues.BASE_URL + "/api/user/login")
    Observable<Result<LoginData>> postUserLogin(@Body LoginRequest request);

    /**
     * 登录页-手机短验找回密码接口
     * @param request
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/user/resetpwd")
    Observable<Result> postSmsFindPwd(@Body LoginRequest request);

    /**
     * 首页-获取 Banner
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/index/getBanner")
    Observable<Result<List<IndexGetBannerBean>>> getIndexGetBanner();

    /**
     * 首页-获取 产品列表
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/product/list")
    Observable<Result<ProductListBean>> getProductList(@Query("page") int page, @Query("limit") int limit);


    /**
     * 文章模块-获取 文章分类
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/Article/cate")
    Observable<Result<List<ArticleCateBean>>> getArticleCate();

    /**
     * 文章模块-获取 文章分类
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/Article/list")
    Observable<Result<ArticleCateListBean>> getArticleList(@Query("cate_id") String cate_id,@Query("page") int page, @Query("limit") int limit);


    /**
     * 注册页-手机短验注册接口
     * @param request
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/scmp-application-mall/register/sms")
    Observable<Result<LoginData>> smsRegister(@Body LoginRequest request);

    /**
     * 设置页-登出
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/scmp-application-mall/authentication/logout")
    Observable<Result> logout();

    /**
     * 全局-文件上传接口
     * @return
     */
    @Multipart
    @POST(ConstValues.BASE_URL + "api/scmp-application-mall/global/file/upload")
    Observable<Result<SubmitFilesBean>> uploadFile(@Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);
    /**
     * 首页-分类列表
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/scmp-application-mall/product/listCategoryTop")
    Observable<Result<List<HomeCategoryData>>> homeListCategoryTop();

    /**
     * 首页-活动分类列表
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/scmp-application-mall/activity/listCategory")
    Observable<Result<List<CategoryDataList>>> listCategory();

    /**
     * 分类页-商品列表
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/scmp-application-mall/product/listProductByCategory")
    Observable<Result<HomeCategoryTypeData>> listProductByCategory(@Query("categoryId") String categoryId);

    /**
     * 首页-活动列表
     * @return
     */
    @GET
    Observable<Result<List<HomeActivityData>>> homeActivityList(@Url String url);
    /**
     * 商品-详情
     * @return
     */
    @GET
    Observable<Result<ShopInfoListData>> productDetail(@Url String url);

    /**
     * 全局-商品搜索
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/scmp-application-mall/product/search")
    Observable<Result<List<ShopInfoData>>> productSearch(@Query("keyword") String keyword);

    /**
     * 商品页-分类列表
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/scmp-application-mall/product/listCategoryAll")
    Observable<Result<List<CommodityCategory>>> getCategoryListAll();

    /**
     * 我的-优惠券列表
     * @return
     */
    @GET(ConstValues.BASE_URL + "/api/scmp-application-mall/userCoupon/list/{status}")
    Observable<Result<List<CouponTemplateData>>> userCouponList(@Path("status") String status);

    /**
     * 领券中心-优惠券列表
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/scmp-application-mall/couponTemplate/list")
    Observable<Result<List<CouponTemplateData>>> couponTemplateList();

    /**
     * 领券中心-领取优惠券
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/scmp-application-mall/couponTemplate/receive")
    Observable<Result> couponTemplateReceive(@Body OrderInfoBean mOrderInfoBean);

    /**
     * 注册-经营类目树
     * @return
     */
    @GET(ConstValues.BASE_URL + "api/scmp-application-mall/content/businessCategoryTree")
    Observable<Result<CategoryTreeData>> businessCategoryTree();

    /**
     * 审核页-提交接口
     * @return
     */
    @POST(ConstValues.BASE_URL + "api/scmp-application-mall/audit/submit")
    Observable<Result<LoginData>> postAuditSubmit(@Body PostAuditSubmitCommand mPostAuditSubmitCommand);



    /**
     * 创建充值订单
     */
    @POST("user/api/v1/user/order/recharge/create")
    Observable<Result<ParamData>> userRechargeOrder(@Query("rechargeId") String rechargeId);



    /**
     * 购物车-添加商品
     */
    @POST("api/scmp-application-mall/shoppingCart/add")
    Observable<Result<ShoppingCartListBean>> userRechargeOrder(@Body OrderInfoBean mOrderInfoBean);

    /**
     * 购物车-减少商品
     */
    @POST("api/scmp-application-mall/shoppingCart/reduce")
    Observable<Result<ShoppingCartListBean>> shoppingCartReduce(@Body OrderInfoBean mOrderInfoBean);

    /**
     * 购物车-用户购物车列表
     */
    @GET("api/scmp-application-mall/shoppingCart/list")
    Observable<Result<ShoppingCartListBean>> shoppingCartList();

    /**
     * 购物车-用户购物车商品数量
     */
    @GET("api/scmp-application-mall/shoppingCart/count")
    Observable<Result<String>> shoppingCartCount();

    /**
     * 购物车-勾选商品
     */
    @POST("api/scmp-application-mall/shoppingCart/checked")
    Observable<Result<ShoppingCartListBean>> shoppingChecked(@Body OrderInfoBean mOrderInfoBean);

    /**
     * 订单确认页-订单预览
     */
    @POST("api/scmp-application-mall/order/preview")
    Observable<Result<ShoppingCartListBean>> orderPreview(@Body OrderInfoBean mOrderInfoBean);

    /**
     * 全局-当前用户信息接口
     */
    @POST("api/scmp-application-mall/customer/info")
    Observable<Result<UserInfoUpdate>> customerInfo();

    /**
     * 全局-当前用户信息接口
     */
    @POST("api/scmp-application-mall/account/info")
    Observable<Result<AccoutInfoBean>> accountInfo();

    /**
     * 全局-当前用户信息接口
     */
    @GET("api/scmp-application-mall/global/adConfig")
    Observable<Result<GlobalAdconfigBean>> globalAdConfig();

    /**
     * 收货地址页-收货地址列表接口
     */
    @POST("api/scmp-application-mall/shippingAddress/list")
    Observable<Result<List<AddressModel>>> getUserAddress();

    /**
     * 收货地址页-删除收货地址接口
     */
    @POST("api/scmp-application-mall/shippingAddress/delete")
    Observable<Result<List<AddressModel>>> getUserAddressDelete(@Body AddressModel mAddressModel);

    /**
     * 收货地址页-添加收货地址接口
     */
    @POST("api/scmp-application-mall/shippingAddress/create")
    Observable<Result> getAddAddress(@Body AddressModel mAddressModel);

    /**
     * 收货地址页-添加收货地址接口
     */
    @POST("api/scmp-application-mall/shippingAddress/update")
    Observable<Result> getUpdateAddress(@Body AddressModel mAddressModel);

    /**
     * 收货地址页-添加收货地址接口
     */
    @POST("api/scmp-application-mall/shippingAddress/defaulted")
    Observable<Result<List<AddressModel>>> getSetDefault(@Body AddressModel mAddressModel);

    /**
     * 订单确认页-提交订单
     */
    @POST("api/scmp-application-mall/order/submit")
    Observable<Result<OrderSubmitData>> postOrderSubmit(@Body PostOrderSubmit mPostOrderSubmit);

    /**
     * 订单确认页-订单预览
     */
    @GET("api/scmp-application-mall/order/preview")
    Observable<Result<OrderPreviewBean>> getOrderPreview();

    /**
     * 订单列表页-订单历史
     */
    @GET("api/scmp-application-mall/order/history")
    Observable<Result<OrderHistoryBean>> getOrderHistoryList(@Query("orderStatusString") String orderStatusString,
                                                             @Query("current") int current,@Query("size") int size);
    /**
     * 订单列表页-订单历史
     */
    @GET("api/scmp-application-mall/order/history")
    Observable<Result<OrderHistoryBean>> getOrderHistoryList(@Query("current") int current,@Query("size") int size,
                                                             @Query("unInvoiced") boolean unInvoiced);

    /**
     * 订单列表页-订单详情
     */
    @GET("api/scmp-application-mall/order/detail/{innerOrderNo}")
    Observable<Result<OrderHistoryDetailBean>> getOrderHistoryDetail(@Path("innerOrderNo") String innerOrderNo);

    /**
     * 订单列表页-取消订单
     * @return
     */
    @POST("api/scmp-application-mall/order/cancel")
    Observable<Result> orderCancel(@Body PostOrderSubmit.OrderCancel mPayCreate);

    /**
     * 订单列表页-再来一单
     * @return
     */
    @POST("api/scmp-application-mall/order/again")
    Observable<Result> orderAgain(@Body PostOrderSubmit.OrderCancel mPayCreate);

    /**
     * 订单列表页-确认收货
     * @return
     */
    @POST("api/scmp-application-mall/order/confirm")
    Observable<Result> orderConfirm(@Body PostOrderSubmit.OrderCancel mPayCreate);

    /**
     * 退款订单列表页-退货-售后
     * @return
     */
    @POST("api/scmp-application-mall/order/refund/history")
    Observable<Result<OrderRefundHistoryBean>> orderRefundHistory(@Body PostOrderSubmit.RefundHistory mRefundHistory);

    /**
     * 订单列表页-申请退款
     * @return
     */
    @POST("api/scmp-application-mall/order/refund")
    Observable<Result> orderRefund(@Body PostOrderSubmit.OrderCancel mRefund);

    /**
     * 设置默认地址
     *
     * @return
     */
    @GET("backstage/api/v1/wallet/recharge/all")
    Observable<Result<RechargeAllBean>> backstageRechargeAll();

    /**
     * 支付页-支付方式列表
     * @return
     */
    @GET("api/scmp-application-mall/pay/channels")
    Observable<Result<List<ChannelsListBean>>> payChannelsList(@Query("orderType") String orderType);


    /**
     * 支付页-预支付
     * @return
     */
    @POST("api/scmp-application-mall/pay/create")
    Observable<Result<PayDataBean>> payCreate(@Body PostOrderSubmit.PayCreate mPayCreate);

    /**
     * 支付页-查询支付结果
     * @return
     */
    @GET("api/scmp-application-mall/pay/query")
    Observable<Result<PayDataBean>> payQuery(@Query("innerOrderNo") String innerOrderNo,@Query("orderType") String orderType);

    /**
     * 订单详情-发票信息-发票补开
     * @return
     */
    @POST("api/scmp-application-mall/receipt/apply")
    Observable<Result> postReceiptApply(@Body PostOrderSubmit.ReceiptApply mReceiptApply);

}
