package com.bw.movie.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.my_guan_zhu.MyDianYingFragment;
import com.bw.movie.fragment.my_guan_zhu.MyYingYuanFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WoGuanZhuActivity extends BaseActivity {

    @BindView(R.id.iv_shap)
    ImageView ivShap;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment> arrayList;
    private ArrayList<String> arrayList1;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_wo_guan_zhu;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        arrayList = new ArrayList<>();
        MyDianYingFragment myDianYingFragment = new MyDianYingFragment();
        MyYingYuanFragment myYingYuanFragment = new MyYingYuanFragment();
        arrayList.add(myDianYingFragment);
        arrayList.add(myYingYuanFragment);
        HuaDong huaDong = new HuaDong(getSupportFragmentManager());
        vp.setAdapter(huaDong);
        arrayList1 = new ArrayList<>();
        arrayList1.add("电影");
        arrayList1.add("影院");
        tab.addTab(tab.newTab().setText(arrayList1.get(0)));
        tab.addTab(tab.newTab().setText(arrayList1.get(1)));
        tab.setupWithViewPager(vp);
    }



    public class HuaDong extends FragmentPagerAdapter {


        public HuaDong(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList1.get(position);
        }
    }
}
