package com.bw.movie.model;

import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.contral.HomePageXiangQingContral;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageXiangQingModel implements HomePageXiangQingContral.getModel {


    @Override
    public void getGuanZhu(int movieId, final CallBackGuanZhu callBackGuanZhu) {
        RetrofitManager.getInstance().getApis().getGuanZhu(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuanZhuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanZhuBean guanZhuBean) {
                        if(callBackGuanZhu!=null){
                            callBackGuanZhu.getGuanZhuSucc(guanZhuBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackGuanZhu!=null){
                            callBackGuanZhu.getGuanZhuFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getXiang(int movieId, final CallBackXiangQing callBackXiangQing) {
        RetrofitManager.getInstance().getApis().getXiangQing(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiangQingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XiangQingBean xiangQingBean) {
                        if(callBackXiangQing!=null){
                            callBackXiangQing.getXiangQingSucc(xiangQingBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackXiangQing!=null){
                            callBackXiangQing.getXiangQingFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
