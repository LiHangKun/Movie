package com.bw.movie.util;




import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.DiBean;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.GouPiaoBean;
import com.bw.movie.bean.GuanJianZiBean;
import com.bw.movie.bean.GuanYingYuanBean;
import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.MyGuanDianyingBean;
import com.bw.movie.bean.PaiQiBean;
import com.bw.movie.bean.PingDianZanBean;
import com.bw.movie.bean.PingHui;
import com.bw.movie.bean.ReMenMovieBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.TianPingHuiFuBean;
import com.bw.movie.bean.TianYingPingBean;
import com.bw.movie.bean.TuiYingBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.bean.XieYingPingBean;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.bean.YingYuanPingBean;
import com.bw.movie.bean.YingYuanXiangBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhengShangYingBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 这个类的作用，是用来声明我们所有需要用到的接口，以方便将来调用
 * 1. 这是一个接口，不是一个类
 * 2. 接口定义完成，我们需要一个一个的写其中的方法
 * 3. 每一个方法，对应一个接口
 * 4. 返回一个 Observable<想要解析Bean类>
 *    注意：Observable 导包的时候 要导 io.reactivex.Observable，别弄错了
 * 5. 添加 @GET @POST 来确定你的请求方式
 * 6. 在 @GET @POST 之后的括号里，添加 接口文档，除了BaseUrl剩余的部分，不包括拼参
 * 7. POST：普通的POST请求，我们需要添加一个 @FormUrlEncoded
 *          然后使用 @Field 添加参数 @Field（参数的key） 要传入的参数值，要传几个就写几个
 *
 * 8. 图片上传：1.必须使用post
 *             2.传入图片的类型是 MultipartBody，需添加 @Body 注解
 */
public interface Apis {
    //注册
    @POST("user/v2/register")
    @FormUrlEncoded
    Observable<RegisterBean> getReg(@Field("nickName") String nickName, @Field("pwd") String pwd, @Field("email") String email, @Field("code") String code);

    //邮箱
    @POST("user/v2/sendOutEmailCode")
    @FormUrlEncoded
    Observable<EmailBean> getEmail(@Field("email") String email);

    //登录
    @POST("user/v2/login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("email") String email, @Field("pwd") String pwd);

    //banner
    @GET("tool/v2/banner")
    Observable<BannerBean> getBanner();

    //热门电影
    @GET("movie/v2/findHotMovieList")
    Observable<ReMenMovieBean> getReMen(@Query("page")int page,@Query("count")int count);

    //上映电影列表
    @GET("movie/v2/findReleaseMovieList")
    Observable<ZhengShangYingBean> getZheng(@Query("page")int page,@Query("count")int count);

    //即将上映
    @GET("movie/v2/findComingSoonMovieList")
    Observable<JiangShangYingBean> getJiang(@Query("page")int page,@Query("count")int count);

    //关键字搜索
    @GET("movie/v2/findMovieByKeyword")
     Observable<GuanJianZiBean> getSearch(@Query("keyword")String keyword, @Query("page")int page,@Query("count")int count);
    //购票

    @POST("movie/v2/verify/buyMovieTickets")
    @FormUrlEncoded
    Observable<GouPiaoBean> getGouPiao(@Field("userId")int userId,@Field("sessionId")String sessionId);

    //预约
    @POST("movie/v2/verify/reserve")
    @FormUrlEncoded
    Observable<YuYueBean> getYuYue(@Field("movieId")int movieId);

    //详情
    @GET("movie/v2/findMoviesDetail")
    Observable<XiangQingBean> getXiangQing(@Query("movieId") int movieId);

    //关注
    @GET("movie/v1/verify/followMovie")
    Observable<GuanZhuBean> getGuanZhu(@Query("movieId") int movieId);

    //影视评价
    @GET("movie/v2/findAllMovieComment")
    Observable<YingPingBean> getYingPing(@Query("movieId") int movieId,@Query("page")int page,@Query("count")int count);

    //写影视评论
    @GET("movie/v1/verify/movieComment")
    Observable<XieYingPingBean> getXieYing(@Query("movieId") int movieId, @Query("commentContent")String commentContent, @Query("score")double score);

    //推荐影院信息
    @GET("cinema/v1/findRecommendCinemas")
    Observable<TuiYingBean> getTuiYing(@Query("page")int page, @Query("count")int count);

    //查询附近影院
    @GET("cinema/v1/findNearbyCinemas")
    Observable<FuYingBean> getFuYing(@Query("longitude")String longitude,@Query("latitude")String latitude,@Query("page")int apge, @Query("count")int count);

    @GET("cinema/v2/findCinemaByRegion")
    Observable<DiBean> getDi(@Query("regionId")int regionId);

    //添加用户对影片的评论
    @POST("movie/v1/verify/movieComment")
    @FormUrlEncoded
    Observable<TianYingPingBean> getTianYing(@Field("movieId")int movid,@Field("commentContent")String commentContent,@Field("score") double score);

    //电影评论点赞
    @POST("movie/v1/verify/movieCommentGreat")
    @FormUrlEncoded
    Observable<PingDianZanBean> getPingDianZan(@Field("commentId")int commentId);

    //查询影评评论回复
    @GET("movie/v1/findCommentReply")
    Observable<PingHui> getPingHui(@Query("commentId")int commentId,@Query("page")int page,@Query("count")int count);

    //添加评论回复
    @POST("movie/v1/verify/commentReply")
    @FormUrlEncoded
    Observable<TianPingHuiFuBean> getTianPingHui(@Field("commentId")int commentId,@Field("replyContent")String replyContent);

    //影院详情
    @GET("cinema/v1/findCinemaInfo")
    Observable<YingYuanXiangBean> getYingYuanXiang(@Query("cinemaId")int cinemaId);

    //影院评论
    @GET("cinema/v1/findAllCinemaComment")
    Observable<YingYuanPingBean> getYingYuanPing(@Query("cinemaId")int cinemaId,@Query("page")int page,@Query("count")int count);

    //电影排期
    @GET("cinema/v2/findCinemaScheduleList")
    Observable<PaiQiBean> getPaiQi(@Query("cinemaId")int cinemaId,@Query("page")int page,@Query("count")int count);

    //查询用户关注电影列表
    @GET("user/v2/verify/findUserFollowMovieList")
    Observable<MyGuanDianyingBean> getGuanDingYing(@Query("page")int page,@Query("count")int count);

    //查询用户关注影院列表
    @GET("user/v2/verify/findUserFollowCinemaList")
    Observable<GuanYingYuanBean> getGuanYingYuan(@Query("page")int page, @Query("count")int count);
}
