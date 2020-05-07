package com.bw.movie.fragment.ying_yuan_xiang;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.yingyuan.YingYuanPingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.YingYuanPingBean;
import com.bw.movie.bean.YingYuanXiangBean;
import com.bw.movie.contral.HomePageYingYuanXiangContral;
import com.bw.movie.presenter.HomePageYingYuanXiangPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragemenYingYuanXiangRight extends BaseFragment implements HomePageYingYuanXiangContral.getView {


    @BindView(R.id.ying_yuan_right_re)
    RecyclerView yingYuanRightRe;
    private YingYuanPingAdapter yingYuanPingAdapter;

    @Override
    protected void initData() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shou(YingYuanXiangBean yingYuanXiangBean){
        Toast.makeText(getContext(), "right收到", Toast.LENGTH_SHORT).show();
        if(yingYuanXiangBean!=null){
            BasePresenter basePresenter = getmPresenter();
            if(basePresenter instanceof HomePageYingYuanXiangPresenter){
                ((HomePageYingYuanXiangPresenter)basePresenter).getYingYuanPing(yingYuanXiangBean.getResult().getId(),1,100);
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
    @Override
    protected BasePresenter initPresenter() {
        return new HomePageYingYuanXiangPresenter(this);
    }

    @Override
    protected void initView(View inflate) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        yingYuanRightRe.setLayoutManager(linearLayoutManager);
        yingYuanPingAdapter = new YingYuanPingAdapter(getContext());
        yingYuanRightRe.setAdapter(yingYuanPingAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.frg_ying_yuan_xiang_right;
    }


    @Override
    public void getYingYuanSucc(YingYuanXiangBean yingYuanXiangBean) {

    }

    @Override
    public void getYingYuanFiuld(String str) {

    }

    @Override
    public void getYingYuanPingSucc(YingYuanPingBean yingYuanPingBean) {
        yingYuanPingAdapter.setData(yingYuanPingBean.getResult());
    }

    @Override
    public void getYingYuanPingFiuld(String str) {

    }
}
