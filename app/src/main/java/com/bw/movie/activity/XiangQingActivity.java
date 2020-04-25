package com.bw.movie.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.contral.HomePageXiangQingContral;
import com.bw.movie.presenter.HomePageXiangQingPresenter;
import com.bw.movie.util.SPUtils;
import com.bw.movie.xiangqing_fragment.XiangFour;
import com.bw.movie.xiangqing_fragment.XiangOne;
import com.bw.movie.xiangqing_fragment.XiangThree;
import com.bw.movie.xiangqing_fragment.XiangTwo;
import com.bw.movie.zidingyi.DrawerLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;

public class XiangQingActivity extends BaseActivity implements HomePageXiangQingContral.getView {


    @BindView(R.id.iv_max)
    ImageView ivMax;
    @BindView(R.id.iv_shap)
    ImageView ivShap;
    @BindView(R.id.tv_ping)
    TextView tvPing;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_s)
    TextView tvS;
    @BindView(R.id.tv_riqi)
    TextView tvRiqi;
    @BindView(R.id.tv_diqu)
    TextView tvDiqu;
    @BindView(R.id.iv_xin)
    ImageView ivXin;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.drawerHandle)
    ImageView drawerHandle;
    @BindView(R.id.drawerContent)
    LinearLayout drawerContent;
    @BindView(R.id.drawer2)
    RelativeLayout drawer2;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.huachu_tab)
    TabLayout huachuTab;
    @BindView(R.id.huachuan_vp)
    ViewPager huachuanVp;

    private ArrayList<String> tablist;
    private ArrayList<Fragment> arrayList;
    private int movieId;
    private XiangOne xiangOne;

    @Override
    public BasePresenter initPresenter() {
        return new HomePageXiangQingPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_xiang_qing;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        movieId = intent.getIntExtra("movieId", 0);

        Toast.makeText(this, "" + movieId, Toast.LENGTH_SHORT).show();
        DrawerLayout drawerLayout = new DrawerLayout(XiangQingActivity.this);
        drawerLayout.setInitialState(DrawerLayout.State.Close);
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageXiangQingPresenter) {
            ((HomePageXiangQingPresenter) basePresenter).getXiang(movieId);
        }

        xiangOne = new XiangOne();
        XiangTwo xiangTwo = new XiangTwo();
        XiangThree xiangThree = new XiangThree();
        XiangFour xiangFour = new XiangFour();
        arrayList = new ArrayList<>();
        arrayList.add(xiangOne);
        arrayList.add(xiangTwo);
        arrayList.add(xiangThree);
        arrayList.add(xiangFour);
        HuaDong huaDong = new HuaDong(getSupportFragmentManager());
        huachuanVp.setAdapter(huaDong);
        tablist = new ArrayList<>();
        tablist.add("介绍");
        tablist.add("预告");
        tablist.add("剧照");
        tablist.add("影评");
        huachuTab.addTab(huachuTab.newTab().setText(tablist.get(0)));
        huachuTab.addTab(huachuTab.newTab().setText(tablist.get(1)));
        huachuTab.addTab(huachuTab.newTab().setText(tablist.get(2)));
        huachuTab.addTab(huachuTab.newTab().setText(tablist.get(3)));
        huachuTab.setupWithViewPager(huachuanVp);

    }

    @Override
    public void getGuanZhuSucc(GuanZhuBean guanZhuBean) {

    }

    @Override
    public void getGuanZhuFiuld(String str) {

    }

    @Override
    public void getXiangQingSucc(XiangQingBean xiangQingBean) {
        Glide.with(this).load(xiangQingBean.getResult().getImageUrl()).into(ivMax);
        tvPing.setText("评分 " + xiangQingBean.getResult().getScore() + "分");
        tvCount.setText("评论 " + xiangQingBean.getResult().getCommentNum() + "万条");
        tvName.setText("" + xiangQingBean.getResult().getName());
        tvType.setText("" + xiangQingBean.getResult().getMovieType() + "动作 / 科 幻 / 美国 ");
        Date date = new Date(xiangQingBean.getResult().getReleaseTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);

        tvRiqi.setText(format + " ");
        tvDiqu.setText("" + xiangQingBean.getResult().getPlaceOrigin() + "上映");
        tvS.setText("" + xiangQingBean.getResult().getDuration());
        SPUtils.putInt(getApplicationContext(),"id","id",movieId);
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
            return tablist.get(position);
        }
    }

    @Override
    public void getXiangQingFiuld(String str) {

    }



}
