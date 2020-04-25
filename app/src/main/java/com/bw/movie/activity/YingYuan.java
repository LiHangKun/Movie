package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.One;
import com.bw.movie.fragment.Three;
import com.bw.movie.fragment.Two;
import com.bw.movie.presenter.HomePageLoginPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YingYuan extends BaseActivity{


    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout mTabLayout;
    private ArrayList<Fragment> list;
    private List<String> tablist;
    private List<Integer> list1;

    @Override
    public BasePresenter initPresenter() {
        return new HomePageLoginPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_ying_yuan;
    }

    public class HuaDong extends FragmentPagerAdapter {

        public HuaDong(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return tablist.get(position);
        }

        public View getTabView(int position){
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_header, null);
            TextView textView=view.findViewById(R.id.tv_header);
            textView.setText(tablist.get(position));
            view.findViewById(R.id.imageView).setBackgroundResource(list1.get(position));

            return view;
        }
    }

    @Override
    public void initView() {

    }

    @SuppressLint("ResourceType")
    @Override
    public void initData() {
        list1 = new ArrayList<>();
        list1.add(R.drawable.rb_one);
        list1.add(R.drawable.rb_two);
        list1.add(R.drawable.rb_three);
        list = new ArrayList<>();
        One one = new One();
        Two two = new Two();
        Three three = new Three();
        list.add(one);
        list.add(two);
        list.add(three);
        HuaDong huaDong = new HuaDong(getSupportFragmentManager());
        vp.setAdapter(huaDong);
        tablist = new ArrayList<>();
        tablist.add("电影");
        tablist.add("影院");
        tablist.add("我的");

        mTabLayout.setupWithViewPager(vp);
        vp.setCurrentItem(0);
        mTabLayout.getTabAt(0).select();
        for (int i = 0; i < mTabLayout.getTabCount(); i++){
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(huaDong.getTabView(i));
            }
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中了tab的逻辑

                View view=tab.getCustomView();
                TextView textView=view.findViewById(R.id.tv_header);

                textView.setVisibility(View.VISIBLE);
                view.findViewById(R.id.imageView).setFocusable(true);

                view.findViewById(R.id.li).setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
                View view=tab.getCustomView();
                TextView textView=view.findViewById(R.id.tv_header);
                textView.setVisibility(View.GONE);
                view.findViewById(R.id.imageView).setFocusable(false);
                int i = Color.parseColor("#1C2243");
                view.findViewById(R.id.li).setBackgroundColor(i);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
            }
        });

    }
}
