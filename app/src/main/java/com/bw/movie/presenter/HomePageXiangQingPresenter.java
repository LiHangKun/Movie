package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.contral.HomePageXiangQingContral;
import com.bw.movie.model.HomePageXiangQingModel;

public class HomePageXiangQingPresenter extends BasePresenter implements HomePageXiangQingContral.getPresetner {


    private HomePageXiangQingModel homePageXiangQingModel;

    public HomePageXiangQingPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePageXiangQingModel = new HomePageXiangQingModel();
    }

    @Override
    public void getGuanZhu(int movieId) {
        homePageXiangQingModel.getGuanZhu(movieId, new HomePageXiangQingContral.getModel.CallBackGuanZhu() {
            @Override
            public void getGuanZhuSucc(GuanZhuBean guanZhuBean) {
                BaseView view = getView();
                if(view instanceof HomePageXiangQingContral.getView){
                    ((HomePageXiangQingContral.getView)view).getGuanZhuSucc(guanZhuBean);
                }
            }

            @Override
            public void getGuanZhuFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageXiangQingContral.getView){
                    ((HomePageXiangQingContral.getView)view).getGuanZhuFiuld(str);
                }
            }
        });
    }

    @Override
    public void getXiang(int movieId) {
        homePageXiangQingModel.getXiang(movieId, new HomePageXiangQingContral.getModel.CallBackXiangQing() {
            @Override
            public void getXiangQingSucc(XiangQingBean xiangQingBean) {
                BaseView view = getView();
                if(view instanceof HomePageXiangQingContral.getView){
                    ((HomePageXiangQingContral.getView)view).getXiangQingSucc(xiangQingBean);
                }
            }

            @Override
            public void getXiangQingFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageXiangQingContral.getView){
                    ((HomePageXiangQingContral.getView)view).getXiangQingFiuld(str);
                }

            }
        });
    }
}
