package com.bw.movie.model;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.PaiQiBean;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.contral.HomePagePaiQiContral;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePagePaiQiModel implements HomePagePaiQiContral.getModel {


    @Override
    public void getPaiQi(int cinemaId, int page, int count, final CallBackPaiQi callBackPaiQi) {
        RetrofitManager.getInstance().getApis().getPaiQi(cinemaId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PaiQiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PaiQiBean paiQiBean) {
                        if(callBackPaiQi!=null){
                            callBackPaiQi.getPaiQiSucc(paiQiBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackPaiQi!=null){
                            callBackPaiQi.getPaiQiFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
