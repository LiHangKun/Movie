package com.bw.movie.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.zidingyi.VerificationSeekBar;
import com.bw.movie.base.BaseActivity;

import butterknife.BindView;

public class WangJiActivity extends BaseActivity {


    @BindView(R.id.wang_zuo)
    ImageView wangZuo;
    @BindView(R.id.wang_youxiang)
    EditText wangYouxiang;
    @BindView(R.id.wang_yan)
    EditText wangYan;

    @BindView(R.id.wang_huo_yan)
    TextView wangHuoYan;
    @BindView(R.id.wang_deng)
    Button wang_deng;
    @BindView(R.id.sb_progress)
    VerificationSeekBar sbProgress;
    Handler handler = new Handler();
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_wang_ji;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
                if (seekBar.getProgress() != seekBar.getMax()) {
                    seekBar.setProgress(0);
                }else if(seekBar.getProgress()==seekBar.getMax()){
                    Toast.makeText(getApplication(), "滑动到最右", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(0);
                        }
                    },1000);


                }
            }
        });
        wang_deng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WangJiActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
