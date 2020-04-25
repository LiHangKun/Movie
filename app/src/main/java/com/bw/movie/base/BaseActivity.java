package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(getLayout());

        ButterKnife.bind(this);
        mPresenter = initPresenter();

        initView();
        initData();
    }

    public abstract P initPresenter();
    public P getmPresenter(){
        if(mPresenter!=null){
            return mPresenter;
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.ondechView();
            mPresenter=null;
        }
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();
}
