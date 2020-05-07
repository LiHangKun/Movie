package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.YingYuanPingBean;
import com.bw.movie.bean.YingYuanXiangBean;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.contral.HomePageYingYuanXiangContral;
import com.bw.movie.model.HomePageLoginModel;
import com.bw.movie.model.HomePageTuiYingModel;
import com.bw.movie.model.HomePageYingYuanXiangModel;

public class HomePageYingYuanXiangPresenter extends BasePresenter implements HomePageYingYuanXiangContral.getPresetner {


    private HomePageYingYuanXiangModel homePageYingYuanXiangModel;

    public HomePageYingYuanXiangPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePageYingYuanXiangModel = new HomePageYingYuanXiangModel();
    }

    @Override
    public void getYingYuan(int cinemaId) {
        homePageYingYuanXiangModel.getYingYuan(cinemaId, new HomePageYingYuanXiangContral.getModel.CallBackYingYuanXiang() {
            @Override
            public void getYingYuanSucc(YingYuanXiangBean yingYuanXiangBean) {
                BaseView view = getView();
                if(view instanceof HomePageYingYuanXiangContral.getView){
                    ((HomePageYingYuanXiangContral.getView)view).getYingYuanSucc(yingYuanXiangBean);
                }
            }

            @Override
            public void getYingYuanFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageYingYuanXiangContral.getView){
                    ((HomePageYingYuanXiangContral.getView)view).getYingYuanFiuld(str);
                }
            }
        });
    }

    @Override
    public void getYingYuanPing(int cinemaId, int page, int count) {
        homePageYingYuanXiangModel.getYingYuanPing(cinemaId, page, count, new HomePageYingYuanXiangContral.getModel.CallBackYingYuanPing() {
            @Override
            public void getYingYuanPingSucc(YingYuanPingBean yingYuanPingBean) {
                BaseView view = getView();
                if(view instanceof HomePageYingYuanXiangContral.getView){
                    ((HomePageYingYuanXiangContral.getView)view).getYingYuanPingSucc(yingYuanPingBean);
                }
            }

            @Override
            public void getYingYuanPingFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageYingYuanXiangContral.getView){
                    ((HomePageYingYuanXiangContral.getView)view).getYingYuanPingFiuld(str);
                }
            }
        });
    }
}
