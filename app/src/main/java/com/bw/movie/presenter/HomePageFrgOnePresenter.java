package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;
import com.bw.movie.contral.HomePageFrgOneContral;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.model.HomePageFrgOneModel;
import com.bw.movie.model.HomePageLoginModel;

public class HomePageFrgOnePresenter extends BasePresenter implements HomePageFrgOneContral.getPresetner {


    private HomePageFrgOneModel homePageFrgOneModel;

    public HomePageFrgOnePresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePageFrgOneModel = new HomePageFrgOneModel();
    }

    @Override
    public void getBanner() {
        homePageFrgOneModel.getBanner(new HomePageFrgOneContral.getModel.CallBackBanner() {
            @Override
            public void getBannerSucc(BannerBean bannerBean) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getBannerSucc(bannerBean);
                }
            }

            @Override
            public void getBannerFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getBannerFiuld(str);
                }
            }
        });
    }

    @Override
    public void getReMen(int page, int count) {
        homePageFrgOneModel.getReMen(page, count, new HomePageFrgOneContral.getModel.CallBackReMen() {
            @Override
            public void getReMenSucc(ReMenMovieBean reMenMovieBean) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getReMenSucc(reMenMovieBean);
                }
            }

            @Override
            public void getReMenFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getReMenFiuld(str);
                }
            }
        });
    }

    @Override
    public void getZhengShangYing(int page, int count) {
        homePageFrgOneModel.getZhengShangYing(page, count, new HomePageFrgOneContral.getModel.CallBackZhengShangYing() {
            @Override
            public void getZhengShangYingSucc(ZhengShangYingBean zhengShangYingBean) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getZhengShangYingSucc(zhengShangYingBean);
                }
            }

            @Override
            public void getZhengShangYingFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getZhengShangYingFiuld(str);
                }
            }
        });
    }

    @Override
    public void JiangShang(int page, int count) {
        homePageFrgOneModel.getJiangShang(page, count, new HomePageFrgOneContral.getModel.CallBackJiangShang() {
            @Override
            public void getJiangShangSucc(JiangShangYingBean jiangShangYingBean) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getJiangShangSucc(jiangShangYingBean);
                }
            }

            @Override
            public void getJiangShangFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getJiangShangFiuld(str);
                }
            }
        });
    }

    @Override
    public void getYuYue(int movidId) {
        homePageFrgOneModel.getYuYue(movidId, new HomePageFrgOneContral.getModel.CallBackYuYue() {
            @Override
            public void getYuYueSucc(YuYueBean yuYueBean) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getYuYueSucc(yuYueBean);
                }
            }

            @Override
            public void getYuYueFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageFrgOneContral.getView){
                    ((HomePageFrgOneContral.getView)view).getYuYueFiuld(str);
                }
            }
        });
    }
}
