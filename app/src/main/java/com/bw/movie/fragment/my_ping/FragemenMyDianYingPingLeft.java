package com.bw.movie.fragment.my_ping;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.MyAdapterDianYingPing;
import com.bw.movie.adapter.MyDianYingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MyDianYingPingBean;
import com.bw.movie.util.RetrofitManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragemenMyDianYingPingLeft extends BaseFragment {


    @BindView(R.id.re)
    RecyclerView re;
    private MyAdapterDianYingPing myAdapterDianYingPing;

    @Override
    protected void initData() {
        wang();
    }

    private void wang() {
        RetrofitManager.getInstance().getApis().getMyDianYingPing(1, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyDianYingPingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyDianYingPingBean myDianYingPingBean) {
                        if(myDianYingPingBean.getMessage().equals("查询成功")){
                            myAdapterDianYingPing.setData(myDianYingPingBean.getResult());
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
        return null;
    }

    @Override
    protected void initView(View inflate) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        re.setLayoutManager(linearLayoutManager);
        myAdapterDianYingPing = new MyAdapterDianYingPing(getContext());
        re.setAdapter(myAdapterDianYingPing);
    }

    @Override
    protected int getLayout() {
        return R.layout.my_dian_ying_ping;
    }



}
