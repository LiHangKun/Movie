package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.GuanJianZiBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.ZhengShangYingBean;
import com.bw.movie.contral.HomePageFrgOneContral;
import com.bw.movie.contral.HomePageSearchContral;
import com.bw.movie.model.HomePageFrgOneModel;
import com.bw.movie.model.HomePageSearchModel;

public class HomePageSearchPresenter extends BasePresenter implements HomePageSearchContral.getPresetner {


    private HomePageSearchModel homePageSearchModel;

    public HomePageSearchPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePageSearchModel = new HomePageSearchModel();
    }


    @Override
    public void getSearch(String keyword, int page, int count) {
        homePageSearchModel.getSearch(keyword, page, count, new HomePageSearchContral.getModel.CallBackSearch() {
            @Override
            public void getSearchSucc(GuanJianZiBean guanJianZiBean) {
                BaseView view = getView();
                if(view instanceof HomePageSearchContral.getView){
                    ((HomePageSearchContral.getView)view).getSearchSucc(guanJianZiBean);
                }
            }

            @Override
            public void getSearchFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageSearchContral.getView){
                    ((HomePageSearchContral.getView)view).getSearchFiuld(str);
                }
            }
        });
    }
}
