package com.bw.movie.xiangqing_fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.PingDianZanBean;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.contral.HomePageYingPingContral;
import com.bw.movie.presenter.HomePageYingPingPresenter;
import com.bw.movie.util.RetrofitManager;
import com.bw.movie.util.SPUtils;
import com.bw.movie.xiangqing_apdapter.YingPingAdapter;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class XiangFour extends BaseFragment implements HomePageYingPingContral.getView {

    @BindView(R.id.ying_re)
    RecyclerView yingRe;
    private YingPingAdapter yingPingAdapter;

    @Override
    protected void initData() {
        int id = SPUtils.getInt(getContext(), "id", "id");
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter instanceof HomePageYingPingPresenter) {
            ((HomePageYingPingPresenter) basePresenter).getYingPing(id, 1, 100);
        }

    }

    @Override
    protected BasePresenter initPresenter() {
        return new HomePageYingPingPresenter(this);
    }

    @Override
    protected void initView(View inflate) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        yingRe.setLayoutManager(linearLayoutManager);
        yingPingAdapter = new YingPingAdapter(getContext());
        yingRe.setAdapter(yingPingAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.xiangqing_fragment_four;
    }


    @Override
    public void getYingPingrSucc(YingPingBean yingPingBean) {
        yingPingAdapter.setData(yingPingBean.getResult());

        yingPingAdapter.setOnClickZan(new YingPingAdapter.OnClickZan() {
            @Override
            public void onclick(int a) {
                RetrofitManager.getInstance().getApis().getPingDianZan(a)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<PingDianZanBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(PingDianZanBean pingDianZanBean) {
                                Toast.makeText(getContext(), ""+pingDianZanBean.getMessage(), Toast.LENGTH_SHORT).show();
                                if(pingDianZanBean.getMessage().equals("点赞成功")){
                                    int id = SPUtils.getInt(getContext(), "id", "id");
                                    BasePresenter basePresenter = getmPresenter();
                                    if (basePresenter instanceof HomePageYingPingPresenter) {
                                        ((HomePageYingPingPresenter) basePresenter).getYingPing(id, 1, 100);
                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

    }

    @Override
    public void getYingPingFiuld(String str) {

    }


}
