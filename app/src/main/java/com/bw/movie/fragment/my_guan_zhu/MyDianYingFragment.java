package com.bw.movie.fragment.my_guan_zhu;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MyDianYingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MyGuanDianyingBean;
import com.bw.movie.presenter.HomePageTuiYingPresenter;
import com.bw.movie.util.RetrofitManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyDianYingFragment extends BaseFragment {


    @BindView(R.id.re)
    RecyclerView re;
    @BindView(R.id.wus_hu_ju)
    RelativeLayout wus_hu_ju;

    @BindView(R.id.src)
    ImageView src;
    @BindView(R.id.ra_two)
    RelativeLayout raTwo;

    private MyDianYingAdapter myDianYingAdapter;

    @Override
    protected void initData() {

        wang();
    }

    public void wang() {
        RetrofitManager.getInstance().getApis().getGuanDingYing(1, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyGuanDianyingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyGuanDianyingBean myGuanDianyingBean) {
                        if (myGuanDianyingBean.getMessage().equals("查询成功")) {
                            wus_hu_ju.setVisibility(View.GONE);
                            myDianYingAdapter.setData(myGuanDianyingBean.getResult());
                        } else {
                            Toast.makeText(getContext(), "" + myGuanDianyingBean.getMessage(), Toast.LENGTH_SHORT).show();
                            raTwo.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected BasePresenter initPresenter() {
        return new HomePageTuiYingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        re.setLayoutManager(linearLayoutManager);
        myDianYingAdapter = new MyDianYingAdapter(getContext());
        re.setAdapter(myDianYingAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.my_guan_zhu_left;
    }



}
