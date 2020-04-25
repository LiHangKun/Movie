package com.bw.movie.xiangqing_apdapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.YingPingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YingPingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<YingPingBean.ResultBean> result;


    public YingPingAdapter(Context context, List<YingPingBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_yingping, null);
        ViewhodlerYingPing viewhodlerYingPing = new ViewhodlerYingPing(inflate);
        return viewhodlerYingPing;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Uri string1 = Uri.parse(result.get(i).getCommentHeadPic());
        ((ViewhodlerYingPing)viewHolder).yingPingImg.setImageURI(string1);
        ((ViewhodlerYingPing)viewHolder).yingPingName.setText(result.get(i).getCommentUserName()+"");
        ((ViewhodlerYingPing)viewHolder).yingPingFen.setText(result.get(i).getScore()+"");
        ((ViewhodlerYingPing)viewHolder).yingPingJieshao.setText(result.get(i).getCommentContent()+"");
        ((ViewhodlerYingPing)viewHolder).yingPingCount.setText(result.get(i).getIsGreat()+"");
        ((ViewhodlerYingPing)viewHolder).ying_ping_zuo_ren.setText(result.get(i).getGreatNum()+"");
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
        @BindView(R.id.ying_ping_tou_re)
        RecyclerView yingPingTouRe;
        @BindView(R.id.ying_ping_count)
        TextView yingPingCount;
        @BindView(R.id.ying_ping_zuo_ren)
        TextView ying_ping_zuo_ren;

        public ViewhodlerYingPing(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
