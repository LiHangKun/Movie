package com.bw.movie.xiangqing_fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.contral.HomePageXiangQingContral;
import com.bw.movie.presenter.HomePageXiangQingPresenter;
import com.bw.movie.util.SPUtils;
import com.bw.movie.xiangqing_apdapter.DirectorAdapter;
import com.bw.movie.xiangqing_apdapter.YanYuanAdapter;

import butterknife.BindView;

public class XiangOne extends BaseFragment implements HomePageXiangQingContral.getView {

    @BindView(R.id.jieshao_jieshao)
    TextView jieshaoJieshao;
    @BindView(R.id.jieshoa_daoyan)
     TextView jieshoaDaoyan;
    @BindView(R.id.jieshao_re)
      RecyclerView jieshaoRe;
    @BindView(R.id.jieshao_retwo)
     RecyclerView jieshaoRetwo;



    @Override
    protected void initData() {
    }

    @Override
    protected BasePresenter initPresenter() {
        return new HomePageXiangQingPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        showDialog();
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView(View inflate) {
        jieshaoRe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        jieshaoRetwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        int id = SPUtils.getInt(getContext(), "id", "id");
        BasePresenter basePresenter = getmPresenter();
        if(basePresenter instanceof HomePageXiangQingPresenter){
            ((HomePageXiangQingPresenter)basePresenter).getXiang(id);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.xiangqing_fragment_one;
    }


    @Override
    public void getGuanZhuSucc(GuanZhuBean guanZhuBean) {

    }

    @Override
    public void getGuanZhuFiuld(String str) {

    }

    @Override
    public void getXiangQingSucc(XiangQingBean xiangQingBean) {
        hindDialog();
        jieshaoJieshao.setText(xiangQingBean.getResult().getSummary()+"");
        jieshoaDaoyan.setText("导演("+xiangQingBean.getResult().getMovieDirector().size()+")");
        GridLayoutManager gridLayoutManagertwo = new GridLayoutManager(getContext(), 4);
        jieshaoRe.setLayoutManager(gridLayoutManagertwo);
        DirectorAdapter directorAdapter = new DirectorAdapter(getContext(), xiangQingBean.getResult().getMovieDirector());
        jieshaoRe.setAdapter(directorAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        jieshaoRetwo.setLayoutManager(gridLayoutManager);
        YanYuanAdapter yanYuanAdapter = new YanYuanAdapter(getContext(), xiangQingBean.getResult().getMovieActor());
        jieshaoRetwo.setAdapter(yanYuanAdapter);
    }

    @Override
    public void getXiangQingFiuld(String str) {

    }
}
