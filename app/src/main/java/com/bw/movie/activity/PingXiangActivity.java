package com.bw.movie.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.PingLunHuiAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.PingHui;
import com.bw.movie.bean.TianPingHuiFuBean;
import com.bw.movie.util.RetrofitManager;
import com.bw.movie.view.seach.OnSearchFocusListener;
import com.bw.movie.view.seach.OnSearchListener;
import com.bw.movie.zidingyi.RatingBar;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PingXiangActivity extends BaseActivity {


    @BindView(R.id.ying_ping_img)
    SimpleDraweeView yingPingImg;
    @BindView(R.id.ying_ping_name)
    TextView yingPingName;
    @BindView(R.id.rbb)
    RatingBar rbb;
    @BindView(R.id.ying_ping_fen)
    TextView yingPingFen;
    @BindView(R.id.ying_ping_shijian)
    TextView yingPingShijian;
    @BindView(R.id.ying_ping_jieshao)
    TextView yingPingJieshao;
    @BindView(R.id.ping_xiang_re)
    RecyclerView pingXiangRe;
    @BindView(R.id.huifu_renshu)
    TextView huifu_renshu;
    @BindView(R.id.huifu_neirong)
    EditText huifu_neirong;

    private PingLunHuiAdapter pingLunHuiAdapter;
    private int id;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_ping_xiang;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        pingXiangRe.setLayoutManager(linearLayoutManager);
        pingLunHuiAdapter = new PingLunHuiAdapter(this);
        pingXiangRe.setAdapter(pingLunHuiAdapter);
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String tupian = intent.getStringExtra("tupian");
        Uri string1 = Uri.parse(tupian);
        yingPingImg.setImageURI(string1);
        yingPingName.setText(intent.getStringExtra("name"));
        double fen = intent.getDoubleExtra("fen", 0.00);
        yingPingFen.setText(fen + "");
        yingPingShijian.setText(intent.getStringExtra("shijian"));
        yingPingJieshao.setText(intent.getStringExtra("neirong"));
        RetrofitManager.getInstance().getApis().getPingHui(id,1,100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PingHui>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PingHui pingHui) {
                        if(pingHui.getMessage().equals("查询成功")){
                            huifu_renshu.setText(pingHui.getResult().size()+"条回复");
                            pingLunHuiAdapter.setData(pingHui.getResult());
                        }else{
                            huifu_renshu.setText(0+"条回复");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        huifu_neirong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huifu_neirong.setFocusable(true);//设置输入框可聚集
                huifu_neirong.setFocusableInTouchMode(true);//设置触摸聚焦
                huifu_neirong.requestFocus();//请求焦点
                huifu_neirong.findFocus();//获取焦点
            }
        });
        huifu_neirong.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //回车等操作
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_GO
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode()
                        && KeyEvent.ACTION_DOWN == event.getAction())) {
                    // 搜索
                    search();
                }
                return true;
            }
        });
    }
    /**
     *  让EditText失去焦点
     */
    public void lostRocus(){
        if(huifu_neirong != null) {
            huifu_neirong.setFocusable(false);
        }
    }

    public void search(){

        lostRocus();
            RetrofitManager.getInstance().getApis().getTianPingHui(id,huifu_neirong.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TianPingHuiFuBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(TianPingHuiFuBean tianPingHuiFuBean) {
                            Toast.makeText(PingXiangActivity.this, ""+tianPingHuiFuBean.getMessage(), Toast.LENGTH_SHORT).show();
                            if(tianPingHuiFuBean.getMessage().equals("回复成功")){
                                RetrofitManager.getInstance().getApis().getPingHui(id,1,100)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<PingHui>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(PingHui pingHui) {
                                                if(pingHui.getMessage().equals("查询成功")){
                                                    huifu_renshu.setText(pingHui.getResult().size()+"条回复");
                                                    pingLunHuiAdapter.setData(pingHui.getResult());
                                                }else{
                                                    huifu_renshu.setText(0+"条回复");
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
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }



}



