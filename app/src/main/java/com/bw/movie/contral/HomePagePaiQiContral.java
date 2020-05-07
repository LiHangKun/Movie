package com.bw.movie.contral;

import com.bw.movie.activity.PaiQiActivity;
import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.PaiQiBean;

public class HomePagePaiQiContral {
    public interface getView extends BaseView {
        void getPaiQiSucc(PaiQiBean paiQiBean);
        void getPaiQiFiuld(String str);


    }
    public interface getPresetner{
        void getPaiQi(int cinemaId,int page,int count);
    }
    public interface getModel{
        void getPaiQi(int cinemaId,int page,int count, CallBackPaiQi callBackPaiQi);
        interface CallBackPaiQi{
            void getPaiQiSucc(PaiQiBean paiQiBean);
            void getPaiQiFiuld(String str);
        }
    }
}
