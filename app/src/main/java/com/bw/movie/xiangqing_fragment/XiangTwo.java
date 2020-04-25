package com.bw.movie.xiangqing_fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.contral.HomePageXiangQingContral;
import com.bw.movie.presenter.HomePageXiangQingPresenter;
import com.bw.movie.util.SPUtils;
import com.bw.movie.xiangqing_apdapter.VideoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class XiangTwo extends BaseFragment implements HomePageXiangQingContral.getView {

    @BindView(R.id.yugao_re)
    RecyclerView yugaoRe;

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter initPresenter() {

        return new HomePageXiangQingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {
        int id = SPUtils.getInt(getContext(), "id", "id");
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageXiangQingPresenter) {
            ((HomePageXiangQingPresenter) basePresenter).getXiang(id);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.xiangqing_fragment_two;
    }

    @Override
    public void getGuanZhuSucc(GuanZhuBean guanZhuBean) {

    }

    @Override
    public void getGuanZhuFiuld(String str) {

    }

    @Override
    public void getXiangQingSucc(XiangQingBean xiangQingBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        yugaoRe.setLayoutManager(linearLayoutManager);
        VideoAdapter videoAdapter = new VideoAdapter(getContext(), xiangQingBean.getResult().getShortFilmList());
        yugaoRe.setAdapter(videoAdapter);
    }

    @Override
    public void getXiangQingFiuld(String str) {

    }


}
