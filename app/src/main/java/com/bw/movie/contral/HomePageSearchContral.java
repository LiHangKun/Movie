package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.GuanJianZiBean;

public class HomePageSearchContral {
    public interface getView extends BaseView {
        void getSearchSucc(GuanJianZiBean guanJianZiBean);
        void getSearchFiuld(String str);


    }
    public interface getPresetner{
        void getSearch(String keyword,int page,int count);

    }
    public interface getModel{
        void getSearch(String keyword,int page,int count,CallBackSearch callBackSearch);
        interface CallBackSearch{
            void getSearchSucc(GuanJianZiBean guanJianZiBean);
            void getSearchFiuld(String str);
        }

    }
}
