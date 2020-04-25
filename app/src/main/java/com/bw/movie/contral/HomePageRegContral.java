package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;

public class HomePageRegContral {
    public interface getView extends BaseView {
        void getLoginSucc(RegisterBean registerBean);
        void getLoginFiuld(String str);

        void getYouSucc(EmailBean emailBean);
        void getYouFiuld(String str);
    }
    public interface getPresetner{
        void getLogin(String nickName, String pwd, String email, String code);

        void getYou(String email);
    }
    public interface getModel{
        void getLogin(String nickName, String pwd, String email, String code, CallBackLogin callBackLogin);
        interface CallBackLogin{
            void getLoginSucc(RegisterBean registerBean);
            void getLoginFiuld(String str);
        }

        void getYou(String email, CallBackYou callBackYou);
        interface CallBackYou{
            void getYouSucc(EmailBean emailBean);
            void getYouFiuld(String str);
        }
    }
}
