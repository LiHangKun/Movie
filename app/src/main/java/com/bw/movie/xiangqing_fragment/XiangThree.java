package com.bw.movie.xiangqing_fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.contral.HomePageXiangQingContral;
import com.bw.movie.presenter.HomePageXiangQingPresenter;
import com.bw.movie.util.SPUtils;
import com.bw.movie.xiangqing_apdapter.JuZhaoAdapter;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.ThumbViewInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class XiangThree extends BaseFragment implements HomePageXiangQingContral.getView {

    @BindView(R.id.juzhao_adapter)
    RecyclerView juzhaoAdapter;

    @Override
    protected void initData() {
        int id = SPUtils.getInt(getContext(), "id", "id");
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageXiangQingPresenter) {
            ((HomePageXiangQingPresenter) basePresenter).getXiang(id);
        }
    }

    @Override
    protected BasePresenter initPresenter() {
        return new HomePageXiangQingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected int getLayout() {
        return R.layout.xiangqing_fragment_three;
    }

    @Override
    public void getGuanZhuSucc(GuanZhuBean guanZhuBean) {

    }

    @Override
    public void getGuanZhuFiuld(String str) {

    }

    @Override
    public void getXiangQingSucc(final XiangQingBean xiangQingBean) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        juzhaoAdapter.setLayoutManager(gridLayoutManager);
        JuZhaoAdapter juZhaoAdapter = new JuZhaoAdapter(getContext(),getActivity(), xiangQingBean.getResult().getPosterList());
        juzhaoAdapter.setAdapter(juZhaoAdapter);



        juZhaoAdapter.setOnClickImg(new JuZhaoAdapter.OnClick() {
            @Override
            public void onclick(int a) {

            }
        });

    }




    @Override
    public void getXiangQingFiuld(String str) {

    }


}
