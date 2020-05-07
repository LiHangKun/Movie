package com.bw.movie.xiangqing_apdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.PingXiangActivity;
import com.bw.movie.bean.PingDianZanBean;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.util.RetrofitManager;
import com.bw.movie.zidingyi.RatingBar;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YingPingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<YingPingBean.ResultBean> result =new ArrayList<>();
    public YingPingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_yingping, null);
        ViewhodlerYingPing viewhodlerYingPing = new ViewhodlerYingPing(inflate);
        return viewhodlerYingPing;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Uri string1 = Uri.parse(result.get(i).getCommentHeadPic());
        ((ViewhodlerYingPing)viewHolder).yingPingImg.setImageURI(string1);
        ((ViewhodlerYingPing)viewHolder).yingPingName.setText(result.get(i).getCommentUserName()+"");
        ((ViewhodlerYingPing)viewHolder).yingPingFen.setText(result.get(i).getScore()+"");
        ((ViewhodlerYingPing)viewHolder).yingPingJieshao.setText(result.get(i).getCommentContent()+"");
        ((ViewhodlerYingPing)viewHolder).yingPingCount.setText(result.get(i).getIsGreat()+"");
        ((ViewhodlerYingPing)viewHolder).ying_ping_zuo_ren.setText(result.get(i).getGreatNum()+"");


        String aformat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(aformat);
        String format = formatter.format(result.get(i).getCommentTime());
        ((ViewhodlerYingPing)viewHolder).ying_ping_shijian.setText(format);
        ((ViewhodlerYingPing)viewHolder).dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickZan.onclick(result.get(i).getCommentId());
            }
        });
        ((ViewhodlerYingPing)viewHolder).ying_ping_bei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PingXiangActivity.class);
                intent.putExtra("id",result.get(i).getCommentId());
                intent.putExtra("name",result.get(i).getCommentUserName());
                intent.putExtra("neirong",result.get(i).getCommentContent());
                intent.putExtra("shijian",result.get(i).getCommentTime());
                intent.putExtra("tupian",result.get(i).getCommentHeadPic());
                intent.putExtra("fen",result.get(i).getScore());
                context.startActivity(intent);
            }
        });


    }
    public OnClickZan mOnClickZan;
    public void setOnClickZan(OnClickZan onClickZan){
        mOnClickZan=onClickZan;
    }

    public void setData( List<YingPingBean.ResultBean> resultt) {
        result=resultt;
        notifyDataSetChanged();
    }

    public interface OnClickZan{
        void onclick(int a);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewhodlerYingPing extends RecyclerView.ViewHolder {
        @BindView(R.id.ying_ping_img)
        SimpleDraweeView yingPingImg;
        @BindView(R.id.ying_ping_name)
        TextView yingPingName;
        @BindView(R.id.ying_ping_fen)
        TextView yingPingFen;
        @BindView(R.id.ying_ping_jieshao)
        TextView yingPingJieshao;
        @BindView(R.id.dianzan)
        ImageView dianzan;

        @BindView(R.id.ying_ping_count)
        TextView yingPingCount;
        @BindView(R.id.ying_ping_zuo_ren)
        TextView ying_ping_zuo_ren;
        @BindView(R.id.ying_ping_shijian)
        TextView ying_ping_shijian;
        @BindView(R.id.rbb)
        RatingBar rbb;
        @BindView(R.id.ying_ping_bei)
        RelativeLayout ying_ping_bei;

        public ViewhodlerYingPing(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
