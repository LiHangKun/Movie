package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;

public class HomePageYingPingContral {
    public interface getView extends BaseView {
        void getYingPingrSucc(YingPingBean yingPingBean);
        void getYingPingFiuld(String str);


    }
    public interface getPresetner{
        void getYingPing(int movieId,int page,int count);

    }
    public interface getModel{
        void getYingPing(int movieId,int page,int count,CallBackYingPing callBackYingPing);
        interface CallBackYingPing{
            void getYingPingSucc(YingPingBean yingPingBean);
            void getYingPingFiuld(String str);
        }

    }
}
