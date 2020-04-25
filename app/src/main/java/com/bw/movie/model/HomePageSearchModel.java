package com.bw.movie.model;

import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.GuanJianZiBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.ZhengShangYingBean;
import com.bw.movie.contral.HomePageFrgOneContral;
import com.bw.movie.contral.HomePageSearchContral;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageSearchModel implements HomePageSearchContral.getModel {


    @Override
    public void getSearch(String keyword, int page, int count, final CallBackSearch callBackSearch) {
        RetrofitManager.getInstance().getApis().getSearch(keyword,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuanJianZiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuanJianZiBean guanJianZiBean) {
                        if(callBackSearch!=null){
                            callBackSearch.getSearchSucc(guanJianZiBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackSearch!=null){
                            callBackSearch.getSearchFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
