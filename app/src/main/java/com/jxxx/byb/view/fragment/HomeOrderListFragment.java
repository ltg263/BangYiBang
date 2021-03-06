package com.jxxx.byb.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.byb.R;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.base.BaseFragment;
import com.jxxx.byb.bean.OrderHistoryBean;
import com.jxxx.byb.bean.OrderHistoryDetailBean;
import com.jxxx.byb.utils.OrderListBntUtils;
import com.jxxx.byb.view.activity.OrderDetailsActivity;
import com.jxxx.byb.view.adapter.HomeOrderAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeOrderListFragment extends BaseFragment {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_not_data)
    TextView tv_not_data;
    int current = 1;
    String orderStatusString;
    private HomeOrderAdapter mHomeOrderAdapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_refresh_list;
    }

    @Override
    protected void initView() {

        Bundle bundle = getArguments();
        Log.w("bundle","bundle"+bundle);
        if(bundle!=null){
            orderStatusString = bundle.getString("orderStatusString");
        }
        myToolbar.setVisibility(View.GONE);
        mHomeOrderAdapter = new HomeOrderAdapter(null);
        mRvList.setAdapter(mHomeOrderAdapter);
        mHomeOrderAdapter.addHeaderView(getTopView());
        mHomeOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                baseStartActivity(OrderDetailsActivity.class,mHomeOrderAdapter.getData().get(position).getInnerOrderNo());
            }
        });
        mHomeOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.bnt_1:
                    case R.id.bnt_2:
                    case R.id.bnt_3:
                        OrderListBntUtils.startOrderType(mContext, ((TextView) view).getText().toString(),
                                mHomeOrderAdapter.getData().get(position), new OrderListBntUtils.ShoppingCartInterface() {
                                    @Override
                                    public void isResult(Boolean isResult, String num) {
                                        if(isResult){
                                            current=1;
                                            initData();
                                        }
                                    }
                                });
                        break;
                }
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull  RefreshLayout refreshLayout) {
                current++;
                initData();
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                current = 1;
                initData();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && current == 1){
            current = 1;
            initData();
        }
    }
    private View getTopView(){
        View view = View.inflate(getActivity(), R.layout.item_home_order, null);
        view.findViewById(R.id.ll).setVisibility(View.VISIBLE);
        view.findViewById(R.id.rl).setVisibility(View.GONE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showLong("???????????????");
            }
        });
        return view;
    }
    @Override
    protected void initData() {
        RetrofitUtil.getInstance().apiService()
                .getOrderHistoryList(orderStatusString,current,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrderHistoryBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OrderHistoryBean> result) {
                        if(isResultOk(result)) {
                            OrderHistoryBean mData = result.getData();
                            if(mData.getTotal()==0){
                                tv_not_data.setVisibility(View.VISIBLE);
                                mRefreshLayout.setVisibility(View.GONE);
                                return;
                            }
                            tv_not_data.setVisibility(View.GONE);
                            mRefreshLayout.setVisibility(View.VISIBLE);
                            List<OrderHistoryDetailBean> records = result.getData().getRecords();
                            if(current == 1){
                                mHomeOrderAdapter.setNewData(records);
                            }else{
                                mHomeOrderAdapter.addData(records);
                            }
                            if(mData.getTotal()<=mHomeOrderAdapter.getData().size()){
                                mRefreshLayout.setNoMoreData(true);
                            }
                            mRefreshLayout.finishLoadMore();
                            mRefreshLayout.finishRefresh();
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
