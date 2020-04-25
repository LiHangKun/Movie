package com.bw.movie.contral;

import com.bw.movie.base.BaseView;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;

public class HomePageFrgOneContral {
    public interface getView extends BaseView {
        void getBannerSucc(BannerBean bannerBean);
        void getBannerFiuld(String str);

        void getReMenSucc(ReMenMovieBean reMenMovieBean);
        void getReMenFiuld(String str);

        void getZhengShangYingSucc(ZhengShangYingBean zhengShangYingBean);
        void getZhengShangYingFiuld(String str);

        void getJiangShangSucc(JiangShangYingBean jiangShangYingBean);
        void getJiangShangFiuld(String str);

        void getYuYueSucc(YuYueBean yuYueBean);
        void getYuYueFiuld(String str);
    }
    public interface getPresetner{
        void getBanner();
        void getReMen(int page,int count);
        void getZhengShangYing(int page,int count);
        void JiangShang(int page,int count);
        void getYuYue(int movidId);
    }
    public interface getModel{
        void getBanner(CallBackBanner callBackBanner);
        interface CallBackBanner{
            void getBannerSucc(BannerBean bannerBean);
            void getBannerFiuld(String str);
        }
        void getReMen(int page,int count,CallBackReMen callBackReMen);
        interface CallBackReMen{
            void getReMenSucc(ReMenMovieBean reMenMovieBean);
            void getReMenFiuld(String str);
        }
        void getZhengShangYing(int page,int count,CallBackZhengShangYing callBackZhengShangYing);
        interface CallBackZhengShangYing{
            void getZhengShangYingSucc(ZhengShangYingBean zhengShangYingBean);
            void getZhengShangYingFiuld(String str);
        }
        void getJiangShang(int page,int count,CallBackJiangShang callBackJiangShang);
        interface CallBackJiangShang{
            void getJiangShangSucc(JiangShangYingBean jiangShangYingBean);
            void getJiangShangFiuld(String str);
        }
        void getYuYue(int movidId,CallBackYuYue callBackYuYue);
        interface CallBackYuYue{
            void getYuYueSucc(YuYueBean yuYueBean);
            void getYuYueFiuld(String str);
        }
    }
}
