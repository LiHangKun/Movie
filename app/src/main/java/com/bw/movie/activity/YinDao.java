package com.bw.movie.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.HomePageLoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YinDao extends BaseActivity {


    @BindView(R.id.img)
    ImageView img;

    @Override
    public BasePresenter initPresenter() {
        return new HomePageLoginPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_yin_dao;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }



    @OnClick(R.id.img)
    public void onViewClicked() {

    }
}
