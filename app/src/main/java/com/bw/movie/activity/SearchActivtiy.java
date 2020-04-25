package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.SearchDianYingAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.GuanJianZiBean;
import com.bw.movie.contral.HomePageSearchContral;
import com.bw.movie.presenter.HomePageSearchPresenter;
import com.bw.movie.view.seach.KylinSearchView;
import com.bw.movie.view.seach.OnSearchListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivtiy extends BaseActivity implements HomePageSearchContral.getView {

    @BindView(R.id.sv_default)
    KylinSearchView svDefault;
    @BindView(R.id.guan_jian_zi_re)
    RecyclerView guanJianZiRe;
    @BindView(R.id.quanbu)
    TextView quanbu;

    @Override
    public BasePresenter initPresenter() {
        return new HomePageSearchPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_activtiy;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        final BasePresenter basePresenter = getmPresenter();
        quanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (basePresenter instanceof HomePageSearchPresenter) {
                    ((HomePageSearchPresenter) basePresenter).getSearch("", 1, 1000);
                }
            }
        });
        svDefault.setOnSearchListener(new OnSearchListener() {
            @Override
            public void search(String content) {
                Toast.makeText(SearchActivtiy.this, "搜索内容： " + content, Toast.LENGTH_SHORT).show();
                if (basePresenter instanceof HomePageSearchPresenter) {
                    ((HomePageSearchPresenter) basePresenter).getSearch(content, 1, 10);
                }
            }
        });


    }


    @Override
    public void getSearchSucc(GuanJianZiBean guanJianZiBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        guanJianZiRe.setLayoutManager(linearLayoutManager);
        SearchDianYingAdapter searchDianYingAdapter = new SearchDianYingAdapter(getApplicationContext(), guanJianZiBean.getResult());
        guanJianZiRe.setAdapter(searchDianYingAdapter);
    }

    @Override
    public void getSearchFiuld(String str) {

    }
}
