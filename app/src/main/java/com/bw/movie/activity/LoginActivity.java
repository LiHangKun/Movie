package com.bw.movie.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contral.HomePageLoginContral;
import com.bw.movie.presenter.HomePageLoginPresenter;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.SPUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements HomePageLoginContral.getView {


    @BindView(R.id.login_zuo)
    ImageView loginZuo;
    @BindView(R.id.login_zhang)
    EditText loginZhang;
    @BindView(R.id.login_mi)
    EditText loginMi;
    @BindView(R.id.login_wangji)
    TextView loginWangji;
    @BindView(R.id.login_liji_zhuce)
    TextView loginLijiZhuce;
    @BindView(R.id.login_deng)
    Button loginDeng;
    @BindView(R.id.weixin_long)
    LinearLayout weixin_long;
    @Override
    public BasePresenter initPresenter() {
        return new HomePageLoginPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        loginWangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WangJiActivity.class);
                startActivity(intent);
            }
        });
        loginDeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zhang = loginZhang.getText().toString();
                String mi = loginMi.getText().toString();
                String encrypt = EncryptUtil.encrypt(mi);
                BasePresenter basePresenter = getmPresenter();
                if(basePresenter instanceof HomePageLoginPresenter){
                    ((HomePageLoginPresenter)basePresenter).getLogin(zhang,encrypt);
                }
            }
        });
        weixin_long.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.wx_api = WXAPIFactory.createWXAPI(getApplicationContext(), Constants.APP_ID, true);
                Constants.wx_api.registerApp(Constants.APP_ID);

                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                Constants.wx_api.sendReq(req);
            }
        });
    }

    @Override
    public void getLoginSucc(LoginBean loginBean) {
        String message = loginBean.getMessage();
        if(message.equals("登陆成功")){
            SPUtils.putString(getApplicationContext(),SPUtils.USERINFO_NAME,SPUtils.USERINFO_KEY_USER_ID,loginBean.getResult().getUserId()+"");
            SPUtils.putString(getApplicationContext(),SPUtils.USERINFO_NAME,SPUtils.USERINFO_KEY_SESSION_ID,loginBean.getResult().getSessionId()+"");
            SPUtils.putString(getApplicationContext(),SPUtils.USERINFO_NAME,SPUtils.USE_HEADPIC,loginBean.getResult().getUserInfo().getHeadPic()+"");
            SPUtils.putString(getApplicationContext(),SPUtils.USERINFO_NAME,SPUtils.USERINFO_NAME,loginBean.getResult().getUserInfo().getNickName()+"");
            Intent intent = new Intent(LoginActivity.this, YingYuan.class);
            startActivity(intent);
        }
    }

    @Override
    public void getLoginFiuld(String str) {

    }



    @OnClick(R.id.login_liji_zhuce)
    public void onViewClicked() {
        Intent intent = new Intent(LoginActivity.this, RegActivity.class);
        startActivity(intent);
    }
}
