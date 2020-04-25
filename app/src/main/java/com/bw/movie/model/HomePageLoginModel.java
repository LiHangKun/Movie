package com.bw.movie.model;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageLoginModel implements HomePageLoginContral.getModel {


    @Override
    public void getLogin(String email, String pwd, final CallBackLogin callBackLogin) {
        RetrofitManager.getInstance().getApis().getLogin(email,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if(callBackLogin!=null){
                            callBackLogin.getLoginSucc(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackLogin!=null){
                            callBackLogin.getLoginFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
