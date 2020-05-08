package com.bw.movie.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.HomePageTuiYingPresenter;
import com.bw.movie.util.SPUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
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

    }
}
