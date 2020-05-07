package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.TianYingPingBean;
import com.bw.movie.util.RetrofitManager;
import com.bw.movie.zidingyi.RatingBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class XieYingPing extends BaseActivity {


    @BindView(R.id.rb)
    RatingBar rb;
    @BindView(R.id.login_zuo)
    ImageView loginZuo;
    @BindView(R.id.xie_ying_name)
    TextView xieYingName;
    @BindView(R.id.xie_ying_tx)
    EditText xieYingTx;
    @BindView(R.id.xie_ying_tijiao)
    Button xieYingTijiao;
    double a;
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_xie_ying_ping;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        final int moviId = intent.getIntExtra("moviId",0);
        xieYingName.setText(name+"");
        //星星改变
        rb.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                a=ratingCount;
                Toast.makeText(XieYingPing.this, "" + ratingCount, Toast.LENGTH_SHORT).show();
            }
        });
        //点击提交
        xieYingTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = xieYingTx.getText().toString();
                RetrofitManager.getInstance().getApis().getTianYing(moviId,string,(a*2))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TianYingPingBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TianYingPingBean tianYingPingBean) {
                                Toast.makeText(XieYingPing.this, ""+tianYingPingBean.getMessage(), Toast.LENGTH_SHORT).show();
                                if(tianYingPingBean.getMessage().equals("评论成功")){
                                    finish();
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
