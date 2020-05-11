package com.bw.movie.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.YiJianFanKuiBean;
import com.bw.movie.util.RetrofitManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YiJianFanKui extends BaseActivity {


    @BindView(R.id.neirong)
    EditText neirong;
    @BindView(R.id.tijiao)
    Button tijiao;
    @BindView(R.id.iv_shap)
    ImageView ivShap;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.ra_one)
    RelativeLayout raOne;
    @BindView(R.id.cheng)
    ImageView cheng;
    @BindView(R.id.wenyi)
    TextView wenyi;
    @BindView(R.id.ra_two)
    RelativeLayout raTwo;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_yi_jian_fan_kui;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        raTwo.setVisibility(View.GONE);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = neirong.getText().toString();
                RetrofitManager.getInstance().getApis().getYiJianFanKui(string)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<YiJianFanKuiBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(YiJianFanKuiBean yiJianFanKuiBean) {
                                Toast.makeText(YiJianFanKui.this, "" + yiJianFanKuiBean.getMessage(), Toast.LENGTH_SHORT).show();
                                if (yiJianFanKuiBean.getMessage().equals("反馈成功")) {
                                    raOne.setVisibility(View.GONE);
                                    raTwo.setVisibility(View.VISIBLE);
                                } else {
                                    raTwo.setVisibility(View.GONE);
                                    raOne.setVisibility(View.VISIBLE);
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



}
