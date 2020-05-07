package com.bw.movie.yingyuan_fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.yingyuan.DiQuAdapter;
import com.bw.movie.adapter.yingyuan.DiQuAdapterTwo;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.DiBean;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.TuiYingBean;
import com.bw.movie.contral.HomePageTuiYingContral;
import com.bw.movie.presenter.HomePageTuiYingPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class YingFrgThree extends BaseFragment implements HomePageTuiYingContral.getView {

    @BindView(R.id.di_re_one)
    RecyclerView diReOne;
    @BindView(R.id.id_re_two)
    RecyclerView idReTwo;

    @Override
    protected void initData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("海淀区");
        arrayList.add("朝阳区");
        arrayList.add("东城区");
        arrayList.add("西城区");
        arrayList.add("石景山区");
        arrayList.add("丰台区");
        arrayList.add("大兴区");
        arrayList.add("通州区");
        arrayList.add("顺义区");
        arrayList.add("昌平区");
        arrayList.add("房山区");
        arrayList.add("门头沟区");
        arrayList.add("怀柔区");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        diReOne.setLayoutManager(linearLayoutManager);
        final DiQuAdapter diQuAdapter = new DiQuAdapter(getContext(),arrayList);
        diReOne.setAdapter(diQuAdapter);
        diQuAdapter.setOnCliCk(new DiQuAdapter.Onclick() {
            @Override
            public void click(int a) {
                BasePresenter basePresenter = getmPresenter();
                if(basePresenter instanceof HomePageTuiYingPresenter){
                    Toast.makeText(getContext(), ""+a, Toast.LENGTH_SHORT).show();
                    ((HomePageTuiYingPresenter)basePresenter).getDi(a+1);
                }

            }
        });

    }

    @Override
    protected BasePresenter initPresenter() {
        return new HomePageTuiYingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_ying_three;
    }


    @Override
    public void getTuiYingSucc(TuiYingBean tuiYingBean) {

    }

    @Override
    public void getTuiYingFiuld(String a) {

    }

    @Override
    public void getFuYingSucc(FuYingBean fuYingBean) {

    }

    @Override
    public void getFuYingFiuld(String a) {

    }

    @Override
    public void getDiSucc(final DiBean diBean) {
        LinearLayoutManager linearLayoutManagertwo = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        idReTwo.setLayoutManager(linearLayoutManagertwo);
        DiQuAdapterTwo diQuAdapterTwo = new DiQuAdapterTwo(getContext(), diBean.getResult());
        idReTwo.setAdapter(diQuAdapterTwo);
    }

    @Override
    public void getDiFiuld(String a) {

    }
}
