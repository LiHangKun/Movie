package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.PaiQiBean;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.contral.HomePagePaiQiContral;
import com.bw.movie.model.HomePageLoginModel;
import com.bw.movie.model.HomePagePaiQiModel;

public class HomePagePaiQiPresenter extends BasePresenter implements HomePagePaiQiContral.getPresetner {


    private HomePagePaiQiModel homePagePaiQiModel;

    public HomePagePaiQiPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePagePaiQiModel = new HomePagePaiQiModel();
    }

    @Override
    public void getPaiQi(int cinemaId, int page, int count) {
        homePagePaiQiModel.getPaiQi(cinemaId, page, count, new HomePagePaiQiContral.getModel.CallBackPaiQi() {
            @Override
            public void getPaiQiSucc(PaiQiBean paiQiBean) {
                BaseView view = getView();
                if(view instanceof HomePagePaiQiContral.getView){
                    ((HomePagePaiQiContral.getView)view).getPaiQiSucc(paiQiBean);
                }
            }

            @Override
            public void getPaiQiFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePagePaiQiContral.getView){
                    ((HomePagePaiQiContral.getView)view).getPaiQiFiuld(str);
                }
            }
        });
    }
}
