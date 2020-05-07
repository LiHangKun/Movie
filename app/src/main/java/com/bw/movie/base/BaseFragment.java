package com.bw.movie.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.bw.movie.R;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    P mPresenter;
    private Dialog mDialog;
    public boolean mViewInflateFinished;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), getLayout(), null);
        mPresenter=initPresenter();
        ButterKnife.bind(this,inflate);
        initView(inflate);
        mViewInflateFinished=true;
        doNetWork();
        return inflate;
    }
    /**
     * 尝试调用网络，先判断是否对用户可见
     * 如果可见，调用抽象方法，让子类去调接口
     */
    private void doNetWork(){
        if(getUserVisibleHint()){
            initData();
        }
    }

    /**
     * fragment 提供的回调，回调当天fragment是否对用用户可见
     * 他是在当这个 fragment 是否对用户的可见发生变化的时候
     * @param isVisibleToUser false对用户不可见， true对用户可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 如果还没有加载过数据 && 用户切换到了这个fragment
        // 那就开始加载数据
        if (mViewInflateFinished && isVisibleToUser) {
            initData();
        }
    }
    public void showDialog(){
        if(mDialog == null){
            mDialog = new Dialog(getContext());
            mDialog.setCancelable(false);
            View view = View.inflate(getContext(), R.layout.dialog_lodding_layout, null);
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

    protected abstract void initData();

    protected abstract P initPresenter();
    public P getmPresenter(){
        if(mPresenter!=null){
            return mPresenter;
        }
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.ondechView();
            mPresenter=null;
        }
    }

    protected abstract void initView(View inflate);

    protected abstract int getLayout();
}
