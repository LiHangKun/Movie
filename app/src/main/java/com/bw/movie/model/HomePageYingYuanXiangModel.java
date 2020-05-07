package com.bw.movie.model;

import com.bw.movie.bean.LoginBean;
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
}
