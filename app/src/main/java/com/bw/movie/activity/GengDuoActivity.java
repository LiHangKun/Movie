package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.apapter_gengduo.GengDuoComingSoonMovieAdapter;
import com.bw.movie.apapter_gengduo.GengDuoReMenAdapter;
import com.bw.movie.apapter_gengduo.GengDuoZhengReYingAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;
import com.bw.movie.contral.HomePageFrgOneContral;
import com.bw.movie.presenter.HomePageFrgOnePresenter;
import com.bw.movie.view.seach.KylinSearchView;
import com.bw.movie.view.seach.OnSearchFocusListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GengDuoActivity extends BaseActivity implements HomePageFrgOneContral.getView {


    @BindView(R.id.gengduo_sousuokuang)
    KylinSearchView shurukuang;

    @BindView(R.id.gengduo_re)
    RecyclerView gengduoRe;
    @BindView(R.id.gengduo_tab)
    TabLayout gengduoTab;
    @BindView(R.id.gengduo_fangfajing)
    ImageView gengduo_fangfajing;
    private static BasePresenter basePresenter;

    public static void setClick(Context context, int movieId) {
        if(basePresenter instanceof HomePageFrgOnePresenter){
            ((HomePageFrgOnePresenter) basePresenter).getYuYue(movieId);
        }

    }

    @Override
    public BasePresenter initPresenter() {
        return new HomePageFrgOnePresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_geng_duo;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("正在热映");
        arrayList.add("即将上映");
        arrayList.add("热门电影");
        gengduoTab.addTab(gengduoTab.newTab().setText(arrayList.get(0)));
        gengduoTab.addTab(gengduoTab.newTab().setText(arrayList.get(1)));
        gengduoTab.addTab(gengduoTab.newTab().setText(arrayList.get(2)));
        basePresenter = getmPresenter();
        if(basePresenter instanceof HomePageFrgOnePresenter){
            ((HomePageFrgOnePresenter) basePresenter).getZhengShangYing(1,100);
        }
        gengduoTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals("正在热映")){

                    if(basePresenter instanceof HomePageFrgOnePresenter){
                        ((HomePageFrgOnePresenter) basePresenter).getZhengShangYing(1,100);
                    }
                }else if(tab.getText().toString().equals("即将上映")){
                    if(basePresenter instanceof HomePageFrgOnePresenter){
                        ((HomePageFrgOnePresenter) basePresenter).JiangShang(1,100);
                    }
                }else{
                    if(basePresenter instanceof HomePageFrgOnePresenter){
                        ((HomePageFrgOnePresenter) basePresenter).getReMen(1,100);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        shurukuang.setVisibility(View.GONE);
        gengduo_fangfajing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shurukuang.setVisibility(View.VISIBLE);
                gengduo_fangfajing.setVisibility(View.GONE);
            }
        });
        shurukuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GengDuoActivity.this, SearchActivtiy.class);
                startActivity(intent);
            }
        });
        shurukuang.setOnSearchFocusListener(new OnSearchFocusListener() {
            @Override
            public void searchFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Intent intent = new Intent(GengDuoActivity.this, SearchActivtiy.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void getBannerSucc(BannerBean bannerBean) {

    }

    @Override
    public void getBannerFiuld(String str) {

    }

    @Override
    public void getReMenSucc(ReMenMovieBean reMenMovieBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        gengduoRe.setLayoutManager(linearLayoutManager);
        GengDuoReMenAdapter gengDuoReMenAdapter = new GengDuoReMenAdapter(getApplicationContext(), reMenMovieBean.getResult());
        gengduoRe.setAdapter(gengDuoReMenAdapter);
    }

    @Override
    public void getReMenFiuld(String str) {

    }

    @Override
    public void getZhengShangYingSucc(ZhengShangYingBean zhengShangYingBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        gengduoRe.setLayoutManager(linearLayoutManager);
        GengDuoZhengReYingAdapter gengDuoZhengReYingAdapter = new GengDuoZhengReYingAdapter(getApplicationContext(), zhengShangYingBean.getResult());
        gengduoRe.setAdapter(gengDuoZhengReYingAdapter);
    }
    @Override
    public void getZhengShangYingFiuld(String str) {

    }

    @Override
    public void getJiangShangSucc(JiangShangYingBean jiangShangYingBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        gengduoRe.setLayoutManager(linearLayoutManager);
        GengDuoComingSoonMovieAdapter gengDuoComingSoonMovieAdapter = new GengDuoComingSoonMovieAdapter(getApplicationContext(), jiangShangYingBean.getResult());
        gengduoRe.setAdapter(gengDuoComingSoonMovieAdapter);
    }

    @Override
    public void getJiangShangFiuld(String str) {

    }

    @Override
    public void getYuYueSucc(YuYueBean yuYueBean) {
        Toast.makeText(this, "" + yuYueBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (yuYueBean.getMessage().equals("预约成功")) {
            Toast.makeText(this, "判断正确", Toast.LENGTH_SHORT).show();
            ((HomePageFrgOnePresenter) basePresenter).JiangShang(1, 16);
        }
    }

    @Override
    public void getYuYueFiuld(String str) {

    }


   
}
