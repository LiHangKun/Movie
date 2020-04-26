package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.yingyuan_fragment.YingFrgOne;
import com.bw.movie.yingyuan_fragment.YingFrgThree;
import com.bw.movie.yingyuan_fragment.YingFrgTwo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class Two extends BaseFragment {

    @BindView(R.id.ying_tab)
    TabLayout yingTab;
    @BindView(R.id.ying_vp)
    ViewPager yingVp;
    private ArrayList<Fragment> frglist;
    private ArrayList<String> arrayList;

    @Override
    protected void initData() {
        frglist = new ArrayList<>();
        YingFrgOne yingFrgOne = new YingFrgOne();
        YingFrgTwo yingFrgTwo = new YingFrgTwo();
        YingFrgThree yingFrgThree = new YingFrgThree();
        frglist.add(yingFrgOne);
        frglist.add(yingFrgTwo);
        frglist.add(yingFrgThree);
        HuaDong huaDong = new HuaDong(getChildFragmentManager());
        yingVp.setAdapter(huaDong);
        arrayList = new ArrayList<>();
        arrayList.add("推荐影院");
        arrayList.add("附近影院");
        arrayList.add("海淀区");
        yingTab.addTab(yingTab.newTab().setText(arrayList.get(0)));
        yingTab.addTab(yingTab.newTab().setText(arrayList.get(1)));
        yingTab.addTab(yingTab.newTab().setText(arrayList.get(2)));
        yingTab.setupWithViewPager(yingVp);
    }



    public class HuaDong extends FragmentPagerAdapter {

        public HuaDong(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return frglist.get(i);
        }

        @Override
        public int getCount() {
            return frglist.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);
        }
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_two;
    }


}
