package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.model.HomePageLoginModel;

public class HomePageLoginPresenter extends BasePresenter implements HomePageLoginContral.getPresetner {


    private HomePageLoginModel homePageLoginModel;

    public HomePageLoginPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        homePageLoginModel = new HomePageLoginModel();
    }

    @Override
    public void getLogin(String email, String pwd) {
        homePageLoginModel.getLogin(email, pwd, new HomePageLoginContral.getModel.CallBackLogin() {
            @Override
            public void getLoginSucc(LoginBean loginBean) {
                BaseView view = getView();
                if(view instanceof HomePageLoginContral.getView){
                    ((HomePageLoginContral.getView)view).getLoginSucc(loginBean);
                }
            }

            @Override
            public void getLoginFiuld(String str) {
                BaseView view = getView();
                if(view instanceof HomePageLoginContral.getView){
                    ((HomePageLoginContral.getView)view).getLoginFiuld(str);
                }
            }
        });
    }
}
