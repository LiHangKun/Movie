package com.bw.movie.model;

import com.bw.movie.bean.DiBean;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.TuiYingBean;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.contral.HomePageTuiYingContral;
import com.bw.movie.contral.HomePageYingPingContral;
import com.bw.movie.contral.HomePageYingPingContral.getModel;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageTuiYingModel implements HomePageTuiYingContral.getModel {

    @Override
    public void getTuiYing(int page, int count, final CallBackTuiYing callBackTuiYing) {
        RetrofitManager.getInstance().getApis().getTuiYing(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TuiYingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TuiYingBean tuiYingBean) {
                        if(callBackTuiYing!=null){
                            callBackTuiYing.getTuiYingSucc(tuiYingBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackTuiYing!=null){
                            callBackTuiYing.getTuiYingFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getFuYing(String longitude, String latitude, int page, int count, final CallBackFuYing callBackFuYing) {
        RetrofitManager.getInstance().getApis().getFuYing(longitude,latitude,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuYingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuYingBean fuYingBean) {
                        if(callBackFuYing!=null){
                            callBackFuYing.getFuYingSucc(fuYingBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackFuYing!=null){
                            callBackFuYing.getFuYingFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getDi(int regionId, final CallBackDi callBackDi) {
        RetrofitManager.getInstance().getApis().getDi(regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DiBean diBean) {
                            if(callBackDi!=null){
                                callBackDi.getDiSucc(diBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackDi!=null){
                            callBackDi.getDiFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
