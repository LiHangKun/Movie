package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contral.HomePageRegContral;
import com.bw.movie.presenter.HomePageRegPresenter;
import com.bw.movie.util.EncryptUtil;

import butterknife.BindView;

import butterknife.OnClick;

public class RegActivity extends BaseActivity implements HomePageRegContral.getView {



    int a = 60;
    @BindView(R.id.reg_zuo)
    ImageView regZuo;
    @BindView(R.id.yiyouzhanghao)
    TextView yiyouzhanghao;
    @BindView(R.id.reg_nicheng)
    EditText regNicheng;
    @BindView(R.id.reg_you)
    EditText regYou;
    @BindView(R.id.reg_pwd)
    EditText regPwd;
    @BindView(R.id.reg_yan)
    EditText regYan;
    @BindView(R.id.reg_huo)
    TextView regHuo;
    @BindView(R.id.zhu)
    Button zhu;

    @Override
    public BasePresenter initPresenter() {
        return new HomePageRegPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.reg_reg;
    }

    @Override
    public void initView() {

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (a > 1) {
                a--;
                regHuo.setText("重新获取(" + a + ")");
                handler.sendEmptyMessageDelayed(0, 1000);
            } else if (a == 1) {
                a = 60;
                regHuo.setText("重新获取");
                regHuo.setClickable(true);
                handler.removeMessages(0);
            }
        }
    };

    @Override
    public void initData() {

        final BasePresenter presenter = getmPresenter();
        zhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegActivity.this, "点击注册了", Toast.LENGTH_SHORT).show();
                String nickName = regNicheng.getText().toString();
                String email = regYou.getText().toString();
                String pwd = regPwd.getText().toString();
                String code = regYan.getText().toString();
                String encrypt = EncryptUtil.encrypt(pwd);
                Log.i("aaaa", "" + encrypt);
                if (presenter instanceof HomePageRegPresenter) {
                    ((HomePageRegPresenter) presenter).getLogin(nickName, encrypt, email, code);
                }
            }
        });
        regHuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regYou.getText().toString();
                regHuo.setClickable(false);
                if (presenter instanceof HomePageRegPresenter) {
                    ((HomePageRegPresenter) presenter).getYou(email);
                }
            }
        });

    }


    @Override
    public void getLoginSucc(RegisterBean registerBean) {

    }

    @Override
    public void getLoginFiuld(String str) {

    }

    @Override
    public void getYouSucc(EmailBean emailBean) {
        if (emailBean.getMessage().equals("发送成功")) {
            handler.sendEmptyMessageDelayed(0, 1000);
        }
        Toast.makeText(this, "" + emailBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getYouFiuld(String str) {

    }


    @OnClick(R.id.yiyouzhanghao)
    public void onViewClicked() {
        Intent intent = new Intent(RegActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
