package com.bw.movie.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.util.SPUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Three extends BaseFragment {


    @BindView(R.id.wode_name)
    TextView wodeName;

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView(View inflate) {
        String string = SPUtils.getString(getContext(), SPUtils.USERINFO_NAME, SPUtils.USE_HEADPIC);
        SimpleDraweeView sim = (SimpleDraweeView)inflate.findViewById(R.id.sim);

        Uri string1 = Uri.parse(string);
        sim.setImageURI(string1);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_three;
    }


}
