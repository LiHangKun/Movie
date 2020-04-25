package com.bw.movie.model;

import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contral.HomePageRegContral;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageRegModel implements HomePageRegContral.getModel {
    @Override
    public void getLogin(String nickName, String pwd, String email, String code, final CallBackLogin callBackLogin) {
        RetrofitManager.getInstance().getApis().getReg(nickName,pwd,email,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if(callBackLogin!=null){
                            callBackLogin.getLoginSucc(registerBean);
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

    @Override
    public void getYou(String email,final CallBackYou callBackYou) {
        RetrofitManager.getInstance().getApis().getEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmailBean emailBean) {
                        if(callBackYou!=null){
                            callBackYou.getYouSucc(emailBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackYou!=null){
                            callBackYou.getYouFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
