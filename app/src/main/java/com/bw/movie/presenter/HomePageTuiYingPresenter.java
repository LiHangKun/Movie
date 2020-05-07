package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.DiBean;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.TuiYingBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;
import com.bw.movie.contral.HomePageFrgOneContral;
import com.bw.movie.contral.HomePageTuiYingContral;
import com.bw.movie.model.HomePageFrgOneModel;
import com.bw.movie.model.HomePageTuiYingModel;

public class HomePageTuiYingPresenter extends BasePresenter implements HomePageTuiYingContral.getPresetner {


    private HomePageTuiYingModel homePageTuiYingModel;

    public HomePageTuiYingPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePageTuiYingModel = new HomePageTuiYingModel();
    }

    @Override
    public void getTuiYing(int page, int count) {
        homePageTuiYingModel.getTuiYing(page, count, new HomePageTuiYingContral.getModel.CallBackTuiYing() {
            @Override
            public void getTuiYingSucc(TuiYingBean tuiYingBean) {
                BaseView view = getView();
                if(view instanceof HomePageTuiYingContral.getView){
                    ((HomePageTuiYingContral.getView)view).getTuiYingSucc(tuiYingBean);
                }
            }

            @Override
            public void getTuiYingFiuld(String a) {
                BaseView view = getView();
                if(view instanceof HomePageTuiYingContral.getView){
                    ((HomePageTuiYingContral.getView)view).getTuiYingFiuld(a);
                }
            }
        });
    }

    @Override
    public void getFuYing(String longitude, String latitude, int page, int count) {
        homePageTuiYingModel.getFuYing(longitude, latitude, page, count, new HomePageTuiYingContral.getModel.CallBackFuYing() {
            @Override
            public void getFuYingSucc(FuYingBean fuYingBean) {
                BaseView view = getView();
                if(view instanceof HomePageTuiYingContral.getView){
                    ((HomePageTuiYingContral.getView)view).getFuYingSucc(fuYingBean);
                }
            }

            @Override
            public void getFuYingFiuld(String a) {
                BaseView view = getView();
                if(view instanceof HomePageTuiYingContral.getView){
                    ((HomePageTuiYingContral.getView)view).getFuYingFiuld(a);
                }
            }
        });
    }

    @Override
    public void getDi(int regionId) {
        homePageTuiYingModel.getDi(regionId, new HomePageTuiYingContral.getModel.CallBackDi() {
            @Override
            public void getDiSucc(DiBean diBean) {
                BaseView view = getView();
                if(view instanceof HomePageTuiYingContral.getView){
                    ((HomePageTuiYingContral.getView)view).getDiSucc(diBean);
                }
            }

            @Override
            public void getDiFiuld(String a) {
                BaseView view = getView();
                if(view instanceof HomePageTuiYingContral.getView){
                    ((HomePageTuiYingContral.getView)view).getDiFiuld(a);
                }
            }
        });
    }
}
