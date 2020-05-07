package com.bw.movie.model;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.YingYuanPingBean;
import com.bw.movie.bean.YingYuanXiangBean;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.contral.HomePageYingYuanXiangContral;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageYingYuanXiangModel implements HomePageYingYuanXiangContral.getModel {


    @Override
    public void getYingYuan(int cinemaId, final CallBackYingYuanXiang callBackYingYuanXiang) {
        RetrofitManager.getInstance().getApis().getYingYuanXiang(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YingYuanXiangBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YingYuanXiangBean yingYuanXiangBean) {
                        if(callBackYingYuanXiang!=null){
                            callBackYingYuanXiang.getYingYuanSucc(yingYuanXiangBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackYingYuanXiang!=null){
                            callBackYingYuanXiang.getYingYuanFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getYingYuanPing(int cinemaId, int page, int count, final CallBackYingYuanPing callBackYingYuanPing) {
        RetrofitManager.getInstance().getApis().getYingYuanPing(cinemaId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YingYuanPingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YingYuanPingBean yingYuanPingBean) {
                        if(callBackYingYuanPing!=null){
                            callBackYingYuanPing.getYingYuanPingSucc(yingYuanPingBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackYingYuanPing!=null){
                            callBackYingYuanPing.getYingYuanPingFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
