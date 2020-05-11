package com.bw.movie.fragment.my_ping;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.yingyuan.YingYuanPingAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MyYingYuanPingBean;
import com.bw.movie.bean.YingYuanPingBean;
import com.bw.movie.bean.YingYuanXiangBean;
import com.bw.movie.contral.HomePageYingYuanXiangContral;
import com.bw.movie.presenter.HomePageYingYuanXiangPresenter;
import com.bw.movie.util.RetrofitManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragemenYingYuanPingRight extends BaseFragment {


    @Override
    protected void initData() {
        wang();
    }

    private void wang() {
        RetrofitManager.getInstance().getApis().getMyYingYuanPing(1,100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyYingYuanPingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyYingYuanPingBean myYingYuanPingBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected int getLayout() {
        return R.layout.my_ying_yuan_ping;
    }
}
