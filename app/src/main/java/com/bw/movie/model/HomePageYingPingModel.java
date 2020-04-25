package com.bw.movie.model;

import com.bw.movie.bean.YingPingBean;
import com.bw.movie.contral.HomePageYingPingContral;
import com.bw.movie.contral.HomePageYingPingContral.getModel;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageYingPingModel implements getModel {
    @Override
    public void getYingPing(int movieId, int page, int count, final CallBackYingPing callBackYingPing) {
        RetrofitManager.getInstance().getApis().getYingPing(movieId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YingPingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YingPingBean yingPingBean) {
                        if(callBackYingPing!=null){
                            callBackYingPing.getYingPingSucc(yingPingBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackYingPing!=null){
                            callBackYingPing.getYingPingFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
