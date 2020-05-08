package com.bw.movie.fragment.my_guan_zhu;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.adapter.MyGuanYingYuanAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.GuanYingYuanBean;
import com.bw.movie.presenter.HomePageTuiYingPresenter;
import com.bw.movie.util.RetrofitManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyYingYuanFragment extends BaseFragment {


    @BindView(R.id.re)
    RecyclerView re;
    Unbinder unbinder;
    @BindView(R.id.src)
    ImageView src;
    @BindView(R.id.wus_hu_ju)
    RelativeLayout wusHuJu;

    @BindView(R.id.ra_two)
    RelativeLayout ratwo;
    private MyGuanYingYuanAdapter myGuanYingYuanAdapter;

    @Override
    protected void initData() {
        wang();
    }

    private void wang() {
        RetrofitManager.getInstance().getApis().getGuanYingYuan(1, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuanYingYuanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanYingYuanBean guanYingYuanBean) {
                        if(guanYingYuanBean.getMessage().equals("查询成功")){
                            myGuanYingYuanAdapter.setData(guanYingYuanBean.getResult());
                            wusHuJu.setVisibility(View.GONE);
                        }else{
                            ratwo.setVisibility(View.GONE);
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
    protected BasePresenter initPresenter() {
        return new HomePageTuiYingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        re.setLayoutManager(linearLayoutManager);
        myGuanYingYuanAdapter = new MyGuanYingYuanAdapter(getContext());
        re.setAdapter(myGuanYingYuanAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.my_guan_zhu_right;
    }



}
