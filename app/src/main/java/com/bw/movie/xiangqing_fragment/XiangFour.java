package com.bw.movie.xiangqing_fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.contral.HomePageYingPingContral;
import com.bw.movie.presenter.HomePageYingPingPresenter;
import com.bw.movie.util.SPUtils;
import com.bw.movie.xiangqing_apdapter.YingPingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class XiangFour extends BaseFragment implements HomePageYingPingContral.getView {

    @BindView(R.id.ying_re)
    RecyclerView yingRe;

    @Override
    protected void initData() {
        int id = SPUtils.getInt(getContext(), "id", "id");
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageYingPingPresenter) {
            ((HomePageYingPingPresenter) basePresenter).getYingPing(id, 1, 100);
        }

    }

    @Override
    protected BasePresenter initPresenter() {
        return new HomePageYingPingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected int getLayout() {
        return R.layout.xiangqing_fragment_four;
    }

    @Override
    public void getYingPingrSucc(YingPingBean yingPingBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        yingRe.setLayoutManager(linearLayoutManager);
        YingPingAdapter yingPingAdapter = new YingPingAdapter(getContext(),yingPingBean.getResult());
        yingRe.setAdapter(yingPingAdapter);

    }

    @Override
    public void getYingPingFiuld(String str) {

    }


}
