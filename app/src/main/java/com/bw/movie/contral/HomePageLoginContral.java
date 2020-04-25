package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;

public class HomePageLoginContral {
    public interface getView extends BaseView {
        void getLoginSucc(LoginBean loginBean);
        void getLoginFiuld(String str);


    }
    public interface getPresetner{
        void getLogin(String email, String pwd);
    }
    public interface getModel{
        void getLogin(String email, String pwd, CallBackLogin callBackLogin);
        interface CallBackLogin{
            void getLoginSucc(LoginBean loginBean);
            void getLoginFiuld(String str);
        }


    }
}
