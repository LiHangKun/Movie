package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.contral.HomePageYingPingContral;
import com.bw.movie.model.HomePageYingPingModel;

public class HomePageYingPingPresenter extends BasePresenter implements HomePageYingPingContral.getPresetner {

    private HomePageYingPingModel homePageYingPingModel;

    public HomePageYingPingPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePageYingPingModel = new HomePageYingPingModel();
    }

    @Override
    public void getYingPing(int movieId, int page, int count) {
        homePageYingPingModel.getYingPing(movieId, page, count, new HomePageYingPingContral.getModel.CallBackYingPing() {
            @Override
            public void getYingPingSucc(YingPingBean yingPingBean) {
                BaseView view = getView();
                if(view instanceof HomePageYingPingContral.getView){
                    ((HomePageYingPingContral.getView)view).getYingPingrSucc(yingPingBean);
                }
            }

            @Override
            public void getYingPingFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageYingPingContral.getView){
                    ((HomePageYingPingContral.getView)view).getYingPingFiuld(str);
                }
            }
        });
    }
}
