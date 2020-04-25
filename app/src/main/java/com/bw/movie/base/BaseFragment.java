package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    P mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), getLayout(), null);
        mPresenter=initPresenter();
        ButterKnife.bind(this,inflate);
        initView(inflate);

        initData();
        return inflate;
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
