package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.YuYueAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ChaYuYueBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.util.RetrofitManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WoYuYue extends BaseActivity {


    @BindView(R.id.re)
    RecyclerView re;
    private YuYueAdapter yuYueAdapter;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_wo_yu_yue;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        re.setLayoutManager(linearLayoutManager);
        yuYueAdapter = new YuYueAdapter(this);
        re.setAdapter(yuYueAdapter);
    }

    @Override
    public void initData() {
        wang();
    }

    private void wang() {
        RetrofitManager.getInstance().getApis().getChaYuYue()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChaYuYueBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChaYuYueBean chaYuYueBean) {
                        if(chaYuYueBean.getMessage().equals("查询成功")){
                            yuYueAdapter.setData(chaYuYueBean.getResult());
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


}
