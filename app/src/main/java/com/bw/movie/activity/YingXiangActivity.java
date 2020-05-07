package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.YingXiangYouShiAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.YingYuanPingBean;
import com.bw.movie.bean.YingYuanXiangBean;
import com.bw.movie.contral.HomePageYingYuanXiangContral;
import com.bw.movie.fragment.ying_yuan_xiang.FragemenYingYuanXiangLeft;
import com.bw.movie.fragment.ying_yuan_xiang.FragemenYingYuanXiangRight;
import com.bw.movie.presenter.HomePageYingYuanXiangPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YingXiangActivity extends BaseActivity implements HomePageYingYuanXiangContral.getView {

    @BindView(R.id.iv_shap)
    ImageView ivShap;
    @BindView(R.id.ying_xiang_name)
    TextView yingXiangName;
    @BindView(R.id.ying_xiang_shifou_guanzhu)
    ImageView yingXiangShifouGuanzhu;
    @BindView(R.id.ying_xinag_re_one)
    RecyclerView yingXinagReOne;
    @BindView(R.id.daozheli)
    TextView daozheli;
    @BindView(R.id.ying_xiang_re_tab)
    TabLayout yingXiangReTab;
    @BindView(R.id.ying_xiang_vp)
    ViewPager yingXiangVp;
    @BindView(R.id.tiao_paiqi)
    LinearLayout tiao_paiqi;
    private YingXiangYouShiAdapter yingXiangYouShiAdapter;
    private ArrayList<Fragment> arrayList;
    private ArrayList<String> arrayList1;
    private FragemenYingYuanXiangLeft fragemenYingYuanXiangLeft;
    private int id;

    @Override
    public BasePresenter initPresenter() {
        return new HomePageYingYuanXiangPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_ying_xiang;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        yingXinagReOne.setLayoutManager(linearLayoutManager);
        yingXiangYouShiAdapter = new YingXiangYouShiAdapter(this);
        yingXinagReOne.setAdapter(yingXiangYouShiAdapter);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageYingYuanXiangPresenter) {
            ((HomePageYingYuanXiangPresenter) basePresenter).getYingYuan(id);
        }
        HuaDong huaDong = new HuaDong(getSupportFragmentManager());
        arrayList = new ArrayList<>();
        fragemenYingYuanXiangLeft = new FragemenYingYuanXiangLeft();
        FragemenYingYuanXiangRight fragemenYingYuanXiangRight = new FragemenYingYuanXiangRight();
        arrayList.add(fragemenYingYuanXiangLeft);
        arrayList.add(fragemenYingYuanXiangRight);
        yingXiangVp.setAdapter(huaDong);
        arrayList1 = new ArrayList<>();
        arrayList1.add("影院详情");
        arrayList1.add("影院评价");
        yingXiangReTab.setupWithViewPager(yingXiangVp);
        tiao_paiqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(YingXiangActivity.this, PaiQiActivity.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
            }
        });
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

    @Override
    public void getYingYuanSucc(YingYuanXiangBean yingYuanXiangBean) {
        if (yingYuanXiangBean.getMessage().equals("查询成功")) {
            yingXiangName.setText(yingYuanXiangBean.getResult().getName()+"");
            if(yingYuanXiangBean.getResult().getFollowCinema()==1){
                yingXiangShifouGuanzhu.setImageResource(R.mipmap.aixin_shixin);
            }else{
                yingXiangShifouGuanzhu.setImageResource(R.mipmap.aixin_shixin_bai);
            }
            YingYuanXiangBean.ResultBean result = yingYuanXiangBean.getResult();
            String label = result.getLabel();
            String[] split = label.split(",");
            ArrayList<String> arrayList = new ArrayList<>();
            for(int i=0;i<split.length;i++){
                arrayList.add(split[i]);
            }
            EventBus.getDefault().post(yingYuanXiangBean);
            yingXiangYouShiAdapter.setData(arrayList);

        }
    }



    @Override
    public void getYingYuanFiuld(String str) {

    }

    @Override
    public void getYingYuanPingSucc(YingYuanPingBean yingYuanPingBean) {

    }

    @Override
    public void getYingYuanPingFiuld(String str) {

    }


}
