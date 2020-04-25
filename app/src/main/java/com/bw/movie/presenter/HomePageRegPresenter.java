package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contral.HomePageRegContral;
import com.bw.movie.model.HomePageRegModel;

public class HomePageRegPresenter extends BasePresenter implements HomePageRegContral.getPresetner {

    private HomePageRegModel homePageLoginModel;

    public HomePageRegPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePageLoginModel = new HomePageRegModel();
    }

    @Override
    public void getLogin(String nickName, String pwd, String email, String code) {
        homePageLoginModel.getLogin(nickName, pwd, email, code, new HomePageRegContral.getModel.CallBackLogin() {
            @Override
            public void getLoginSucc(RegisterBean registerBean) {
                BaseView view = getView();
                if(view instanceof HomePageRegContral.getView){
                    ((HomePageRegContral.getView)view).getLoginSucc(registerBean);
                }
            }

            @Override
            public void getLoginFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageRegContral.getView){
                    ((HomePageRegContral.getView)view).getLoginFiuld(str);
                }
            }
        });
    }

    @Override
    public void getYou(String email) {
        homePageLoginModel.getYou(email, new HomePageRegContral.getModel.CallBackYou() {
            @Override
            public void getYouSucc(EmailBean emailBean) {
                BaseView view = getView();
                if(view instanceof HomePageRegContral.getView){
                    ((HomePageRegContral.getView)view).getYouSucc(emailBean);
                }
            }

            @Override
            public void getYouFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageRegContral.getView){
                    ((HomePageRegContral.getView)view).getYouFiuld(str);
                }
            }
        });
    }
}
