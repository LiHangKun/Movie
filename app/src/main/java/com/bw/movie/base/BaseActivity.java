package com.bw.movie.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bw.movie.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    P mPresenter;
    private Dialog mDialog;
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
    //转圈圈
    public void showDialog(){
        if(mDialog == null){
            mDialog = new Dialog(this);
            mDialog.setCancelable(false);
            View view = View.inflate(this, R.layout.dialog_lodding_layout, null);
            ImageView iv = view.findViewById(R.id.iv_dialog_layout);
            Glide.with(this).asGif().load(R.mipmap.loading).into(iv);
            mDialog.addContentView(view
                    , new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                            ,ViewGroup.LayoutParams.WRAP_CONTENT));
            mDialog.show();
        }
    }
    //隐藏
    public void hindDialog(){
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
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
    //点击空白处
    //事件分发控制
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    /**
     * 判定是否需要隐藏
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    /**
     * 隐藏软键盘
     */
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    //点击空白处完
}
