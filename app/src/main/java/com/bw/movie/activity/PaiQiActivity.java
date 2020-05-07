package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.PaiQiAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.PaiQiBean;
import com.bw.movie.contral.HomePagePaiQiContral;
import com.bw.movie.presenter.HomePagePaiQiPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaiQiActivity extends BaseActivity implements HomePagePaiQiContral.getView {

    @BindView(R.id.iv_shap)
    ImageView ivShap;
    @BindView(R.id.paiqi_re_shang)
    RecyclerView paiqiReShang;
    @BindView(R.id.paiqi_re_xia)
    RecyclerView paiqiReXia;

    @Override
    public BasePresenter initPresenter() {
        return new HomePagePaiQiPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_pai_qi;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Toast.makeText(this, "影院id是" + id, Toast.LENGTH_SHORT).show();
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof HomePagePaiQiPresenter) {
            ((HomePagePaiQiPresenter) basePresenter).getPaiQi(id, 1, 100);
        }
    }

    @Override
    public void getPaiQiSucc(PaiQiBean paiQiBean) {
        if (paiQiBean.getMessage().equals("查询成功")) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            paiqiReXia.setLayoutManager(linearLayoutManager);
            PaiQiAdapter paiQiAdapter = new PaiQiAdapter(this, paiQiBean.getResult());
            paiqiReXia.setAdapter(paiQiAdapter);
        }

    }

    @Override
    public void getPaiQiFiuld(String str) {

    }


}
