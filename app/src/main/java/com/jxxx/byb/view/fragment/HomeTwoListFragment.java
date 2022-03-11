package com.jxxx.byb.view.fragment;


import androidx.recyclerview.widget.RecyclerView;

import com.jxxx.byb.R;
import com.jxxx.byb.api.Result;
import com.jxxx.byb.api.RetrofitUtil;
import com.jxxx.byb.base.BaseFragment;
import com.jxxx.byb.utils.GlideImageLoader;
import com.jxxx.byb.view.adapter.HomTwoAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeTwoListFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private HomTwoAdapter mHomTwoAdapter;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home_two_list;
    }

    @Override
    protected void initView() {
        mRvList.setHasFixedSize(true);
        mHomTwoAdapter = new HomTwoAdapter(null);
        mRvList.setAdapter(mHomTwoAdapter);
    }

    @Override
    protected void initData() {
        RetrofitUtil.getInstance().apiService()
                .getIndexGetBanner()
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
//                            if(result.getData()!=null){
                                ArrayList<String> list_path = new ArrayList<>();
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                list_path.add("");
                                mHomTwoAdapter.setNewData(list_path);
//                            }
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



