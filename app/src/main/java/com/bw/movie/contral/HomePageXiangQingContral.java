package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;

public class HomePageXiangQingContral {
    public interface getView extends BaseView {
        void getGuanZhuSucc(GuanZhuBean guanZhuBean);
        void getGuanZhuFiuld(String str);

        void getXiangQingSucc(XiangQingBean xiangQingBean);
        void getXiangQingFiuld(String str);
    }
    public interface getPresetner{
        void getGuanZhu(int movieId);
        void getXiang(int movieId);
    }
    public interface getModel{
        void getGuanZhu(int movieId,CallBackGuanZhu callBackGuanZhu);
        interface CallBackGuanZhu{
            void getGuanZhuSucc(GuanZhuBean guanZhuBean);
            void getGuanZhuFiuld(String str);
        }
        void getXiang(int movieId,CallBackXiangQing callBackXiangQing);
        interface CallBackXiangQing{
            void getXiangQingSucc(XiangQingBean xiangQingBean);
            void getXiangQingFiuld(String str);
        }

    }
}
