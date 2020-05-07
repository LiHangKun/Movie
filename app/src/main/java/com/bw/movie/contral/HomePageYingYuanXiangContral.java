package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.YingYuanXiangBean;

public class HomePageYingYuanXiangContral {
    public interface getView extends BaseView {
        void getYingYuanSucc(YingYuanXiangBean yingYuanXiangBean);
        void getYingYuanFiuld(String str);

    }
    public interface getPresetner{
        void getYingYuan(int cinemaId);
    }
    public interface getModel{
        void getYingYuan(int cinemaId, CallBackYingYuanXiang callBackYingYuanXiang);
        interface CallBackYingYuanXiang{
            void getYingYuanSucc(YingYuanXiangBean yingYuanXiangBean);
            void getYingYuanFiuld(String str);
        }


    }
}
