package com.bw.movie.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MyXiangBean;
import com.bw.movie.bean.UpLoadHeadPicBean;
import com.bw.movie.util.RetrofitManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wildma.pictureselector.PictureSelector;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class MyXiangActivity extends BaseActivity {


    @BindView(R.id.iv_shap)
    ImageView ivShap;
    @BindView(R.id.tv_tou)
    TextView tvTou;
    @BindView(R.id.sdv)
    SimpleDraweeView sdv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.tv_riqi)
    TextView tvRiqi;
    @BindView(R.id.riqi)
    TextView riqi;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_you)
    TextView tvYou;
    @BindView(R.id.tv_youxiang)
    TextView tvYouxiang;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_my_xiang;
    }

    @Override
    public void initView() {

    }

    private void wang() {
        RetrofitManager.getInstance().getApis().getMyXiang()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyXiangBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyXiangBean myXiangBean) {
                        if (myXiangBean.getMessage().equals("查询成功")) {
                            Uri parse = Uri.parse(myXiangBean.getResult().getHeadPic());
                            sdv.setImageURI(parse);
                            name.setText(myXiangBean.getResult().getNickName());
                            if (myXiangBean.getResult().getSex() == 1) {
                                sex.setText("男");
                            } else {
                                sex.setText("女");
                            }
                            //日期
                            Date date = new Date(myXiangBean.getResult().getLastLoginTime());
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String format = dateFormat.format(date);
                            String[] split = format.split("-");
                            riqi.setText(split[1] + "月" + split[2] + "日" + "");
                            tvYouxiang.setText(myXiangBean.getResult().getEmail() + "");
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


    @Override
    public void initData() {
        wang();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==PictureSelector.SELECT_REQUEST_CODE){
            if (data != null) {
                String stringExtra = data.getStringExtra(PictureSelector.PICTURE_PATH);
                File file = new File(stringExtra);
                ArrayList<File> files = new ArrayList<>();
                files.add(file);
                HashMap<String, String> map = new HashMap<>();
                RequestBody requsetBody = RetrofitManager.getInstance().getRequsetBody(files, map);
                    RetrofitManager.getInstance().getApis().getUpLoadHeadPicBean(requsetBody)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<UpLoadHeadPicBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(UpLoadHeadPicBean upLoadHeadPicBean) {
                                    Toast.makeText(MyXiangActivity.this, ""+upLoadHeadPicBean.getMessage(), Toast.LENGTH_SHORT).show();
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
    }




    @OnClick(R.id.sdv)
    public void onViewClicked() {
        PictureSelector.create(MyXiangActivity.this, PictureSelector.SELECT_REQUEST_CODE).selectPicture(true, 200, 200, 1, 1);
    }
}
