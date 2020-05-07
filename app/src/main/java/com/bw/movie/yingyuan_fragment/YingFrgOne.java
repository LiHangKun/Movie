package com.bw.movie.yingyuan_fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.activity.YingXiangActivity;
import com.bw.movie.adapter.yingyuan.TuiYingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.DiBean;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.TuiYingBean;
import com.bw.movie.contral.HomePageTuiYingContral;
import com.bw.movie.presenter.HomePageTuiYingPresenter;

import butterknife.BindView;


public class YingFrgOne extends BaseFragment implements HomePageTuiYingContral.getView {

    @BindView(R.id.re)
    RecyclerView re;

    @Override
    protected void initData() {
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageTuiYingPresenter) {
            ((HomePageTuiYingPresenter) basePresenter).getTuiYing(1, 1000);
        }

    }

    @Override
    protected BasePresenter initPresenter() {
        return new HomePageTuiYingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_ying_one;
    }


    @Override
    public void getTuiYingSucc(TuiYingBean tuiYingBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        re.setLayoutManager(linearLayoutManager);
        TuiYingAdapter tuiYingAdapter = new TuiYingAdapter(getContext(),tuiYingBean.getResult());
        re.setAdapter(tuiYingAdapter);
        tuiYingAdapter.setOnclickBei(new TuiYingAdapter.OnClickBei() {
            @Override
            public void onclick(int a) {
                Intent intent = new Intent(getContext(), YingXiangActivity.class);
                intent.putExtra("id",a);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getTuiYingFiuld(String a) {

    }

    @Override
    public void getFuYingSucc(FuYingBean fuYingBean) {

    }

    @Override
    public void getFuYingFiuld(String a) {

    }

    @Override
    public void getDiSucc(DiBean diBean) {

    }

    @Override
    public void getDiFiuld(String a) {

    }


}
