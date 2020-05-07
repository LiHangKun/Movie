package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.DiBean;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.TuiYingBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;

public class HomePageTuiYingContral {
    public interface getView extends BaseView {
        void getTuiYingSucc(TuiYingBean tuiYingBean);
        void getTuiYingFiuld(String a);

        void getFuYingSucc(FuYingBean fuYingBean);
        void getFuYingFiuld(String a);

        void getDiSucc(DiBean diBean);
        void getDiFiuld(String a);
    }
    public interface getPresetner{
        void getTuiYing(int page,int count);
        void getFuYing(String longitude,String latitude,int page,int count);
        void getDi(int regionId);
    }
    public interface getModel{
        void getTuiYing(int page,int count,CallBackTuiYing callBackTuiYing);
        interface CallBackTuiYing{
            void getTuiYingSucc(TuiYingBean tuiYingBean);
            void getTuiYingFiuld(String a);
        }
        void getFuYing(String longitude,String latitude,int page,int count,CallBackFuYing callBackFuYing);
        interface CallBackFuYing{
            void getFuYingSucc(FuYingBean fuYingBean);
            void getFuYingFiuld(String a);
        }
        void getDi(int regionId,CallBackDi callBackDi);
        interface CallBackDi{
            void getDiSucc(DiBean diBean);
            void getDiFiuld(String a);
        }
    }
}
