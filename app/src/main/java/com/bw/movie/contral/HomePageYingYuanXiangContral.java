package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.YingYuanPingBean;
import com.bw.movie.bean.YingYuanXiangBean;

public class HomePageYingYuanXiangContral {
    public interface getView extends BaseView {
        void getYingYuanSucc(YingYuanXiangBean yingYuanXiangBean);
        void getYingYuanFiuld(String str);

        void getYingYuanPingSucc(YingYuanPingBean yingYuanPingBean);
        void getYingYuanPingFiuld(String str);
    }
    public interface getPresetner{
        void getYingYuan(int cinemaId);
        void getYingYuanPing(int cinemaId,int page,int count);
    }
    public interface getModel{
        void getYingYuan(int cinemaId, CallBackYingYuanXiang callBackYingYuanXiang);
        interface CallBackYingYuanXiang{
            void getYingYuanSucc(YingYuanXiangBean yingYuanXiangBean);
            void getYingYuanFiuld(String str);
        }
        void getYingYuanPing(int cinemaId,int page,int count,CallBackYingYuanPing callBackYingYuanPing);
        interface CallBackYingYuanPing{
            void getYingYuanPingSucc(YingYuanPingBean yingYuanPingBean);
            void getYingYuanPingFiuld(String str);
        }
    }
}
