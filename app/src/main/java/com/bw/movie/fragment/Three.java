package com.bw.movie.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.MyPingLunActivity;
import com.bw.movie.activity.MyXiangActivity;
import com.bw.movie.activity.WoGuanZhuActivity;
import com.bw.movie.activity.WoYuYue;
import com.bw.movie.activity.YiJianFanKui;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.HomePageTuiYingPresenter;
import com.bw.movie.util.SPUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Three extends BaseFragment {


    @BindView(R.id.wode_name)
    TextView wodeName;
    @BindView(R.id.sim)
    SimpleDraweeView sim;
    Unbinder unbinder;
    @BindView(R.id.wode_guanzhu)
    LinearLayout wodeGuanzhu;
    Unbinder unbinder1;
    @BindView(R.id.my_yu_yue)
    LinearLayout myYuYue;
    Unbinder unbinder2;
    @BindView(R.id.wo_ping_lun)
    LinearLayout woPingLun;
    Unbinder unbinder3;
    @BindView(R.id.wo_xiang)
    LinearLayout woXiang;
    @BindView(R.id.yijian_fankui)
    LinearLayout yijianFankui;


    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new HomePageTuiYingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {
        String string = SPUtils.getString(getContext(), SPUtils.USERINFO_NAME, SPUtils.USE_HEADPIC);
        SimpleDraweeView sim = (SimpleDraweeView) inflate.findViewById(R.id.sim);

        Uri string1 = Uri.parse(string);
        sim.setImageURI(string1);
        wodeName.setText(SPUtils.getString(getContext(), SPUtils.USERINFO_NAME, SPUtils.USERINFO_NAME));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_three;
    }


    @OnClick(R.id.wode_guanzhu)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), WoGuanZhuActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.my_yu_yue)
    public void yuyue() {
        Intent intent = new Intent(getContext(), WoYuYue.class);
        startActivity(intent);
    }


    @OnClick(R.id.wo_ping_lun)
    public void onwo_ping_lun() {
        Intent intent = new Intent(getContext(), MyPingLunActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.wo_xiang)
    public void onwo_xiang() {
        Intent intent = new Intent(getContext(), MyXiangActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.yijian_fankui)
    public void onYijianfankui() {
        Intent intent = new Intent(getContext(), YiJianFanKui.class);
        startActivity(intent);
    }
}
