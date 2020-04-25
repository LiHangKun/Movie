package com.bw.movie.model;

import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;
import com.bw.movie.contral.HomePageFrgOneContral;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.util.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageFrgOneModel implements HomePageFrgOneContral.getModel {


    @Override
    public void getBanner(final CallBackBanner callBackBanner) {
        RetrofitManager.getInstance().getApis().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        if(callBackBanner!=null){
                            callBackBanner.getBannerSucc(bannerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackBanner!=null){
                            callBackBanner.getBannerFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getReMen(int page, int count, final CallBackReMen callBackReMen) {
        RetrofitManager.getInstance().getApis().getReMen(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReMenMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReMenMovieBean reMenMovieBean) {
                        if(callBackReMen!=null){
                            callBackReMen.getReMenSucc(reMenMovieBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackReMen!=null){
                            callBackReMen.getReMenFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getZhengShangYing(int page, int count, final CallBackZhengShangYing callBackZhengShangYing) {
        RetrofitManager.getInstance().getApis().getZheng(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<ZhengShangYingBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(ZhengShangYingBean zhengShangYingBean) {
                       if(callBackZhengShangYing!=null){
                           callBackZhengShangYing.getZhengShangYingSucc(zhengShangYingBean);
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       if(callBackZhengShangYing!=null){
                           callBackZhengShangYing.getZhengShangYingFiuld(e.getMessage());
                       }
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }

    @Override
    public void getJiangShang(int page, int count, final CallBackJiangShang callBackJiangShang) {
        RetrofitManager.getInstance().getApis().getJiang(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JiangShangYingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JiangShangYingBean jiangShangYingBean) {
                        if(callBackJiangShang!=null){
                            callBackJiangShang.getJiangShangSucc(jiangShangYingBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackJiangShang!=null){
                            callBackJiangShang.getJiangShangFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getYuYue(int movidId, final CallBackYuYue callBackYuYue) {
        RetrofitManager.getInstance().getApis().getYuYue(movidId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YuYueBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YuYueBean yuYueBean) {
                        if(callBackYuYue!=null){
                            callBackYuYue.getYuYueSucc(yuYueBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackYuYue!=null){
                            callBackYuYue.getYuYueFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
