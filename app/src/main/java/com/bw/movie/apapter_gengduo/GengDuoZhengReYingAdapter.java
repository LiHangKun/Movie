package com.bw.movie.apapter_gengduo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ZhengShangYingBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GengDuoZhengReYingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<ZhengShangYingBean.ResultBean> list;


    public GengDuoZhengReYingAdapter(Context context, List<ZhengShangYingBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_gengduo_releasemovie, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.i("aaaaaa", list.get(i).getName());
        ((ViewHolder) viewHolder).gengRePing.setText(String.valueOf("评分: "+list.get(i).getScore()));
        ((ViewHolder) viewHolder).gengduoZhengReName.setText(list.get(i).getName());
        ((ViewHolder) viewHolder).gengDuoReDaoYan.setText("导演: "+list.get(i).getDirector());
        ((ViewHolder) viewHolder).zhengReZhu.setText("主演: "+list.get(i).getStarring());
        Glide.with(context).load(list.get(i).getImageUrl()).into(((ViewHolder) viewHolder).goupiaoZhengReImg);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goupiao_zheng_re_img)
        ImageView goupiaoZhengReImg;
        @BindView(R.id.gengduo_zheng_re_name)
        TextView gengduoZhengReName;
        @BindView(R.id.geng_duo_re_dao_yan)
        TextView gengDuoReDaoYan;
        @BindView(R.id.zheng_re_zhu)
        TextView zhengReZhu;
        @BindView(R.id.geng_re_ping)
        TextView gengRePing;
        @BindView(R.id.geng_re_yu_yue)
        TextView gengReYuYue;
        @BindView(R.id.gengduo_ll)
        RelativeLayout gengduoLl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
