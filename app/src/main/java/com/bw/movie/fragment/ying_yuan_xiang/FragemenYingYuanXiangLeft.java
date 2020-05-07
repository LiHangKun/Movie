package com.bw.movie.fragment.ying_yuan_xiang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.YingYuanXiangBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragemenYingYuanXiangLeft extends BaseFragment {

    @BindView(R.id.xiang_left_didian)
    TextView xiangLeftDidian;
    @BindView(R.id.ying_xiang_dianhua)
    TextView yingXiangDianhua;
    @BindView(R.id.ying_xiang_fangshi)
     TextView yingXiangFangshi;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shou(YingYuanXiangBean yingYuanXiangBean){
        if(xiangLeftDidian!=null){
            yingXiangFangshi.setText(yingYuanXiangBean.getResult().getVehicleRoute()+"");
            xiangLeftDidian.setText(yingYuanXiangBean.getResult().getAddress()+"");
            yingXiangDianhua.setText(yingYuanXiangBean.getResult().getPhone()+"");
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
    protected void initData() {


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
        return R.layout.frg_ying_yuan_xiang_left;
    }



}
