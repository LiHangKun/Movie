package com.bw.movie.yingyuan_fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.yingyuan.FuYingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.DiBean;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.TuiYingBean;
import com.bw.movie.contral.HomePageTuiYingContral;
import com.bw.movie.presenter.HomePageTuiYingPresenter;
import com.bw.movie.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class YingFrgTwo extends BaseFragment implements HomePageTuiYingContral.getView {

    @BindView(R.id.fu_re)
    RecyclerView fuRe;


    @Override
    protected void initData() {
        String jingdu = SPUtils.getString(getContext(), SPUtils.USERINFO_NAME, "jingdu");
        String weidu = SPUtils.getString(getContext(), SPUtils.USERINFO_NAME, "weidu");
        BasePresenter basePresenter = getmPresenter();
        if(basePresenter instanceof HomePageTuiYingPresenter){
            ((HomePageTuiYingPresenter)basePresenter).getFuYing(jingdu,weidu,1,100);
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
        return R.layout.fragment_ying_two;
    }


    @Override
    public void getTuiYingSucc(TuiYingBean tuiYingBean) {

    }

    @Override
    public void getTuiYingFiuld(String a) {

    }

    @Override
    public void getFuYingSucc(FuYingBean fuYingBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        fuRe.setLayoutManager(linearLayoutManager);
        FuYingAdapter fuYingAdapter = new FuYingAdapter(getContext(), fuYingBean.getResult());
        fuRe.setAdapter(fuYingAdapter);
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
