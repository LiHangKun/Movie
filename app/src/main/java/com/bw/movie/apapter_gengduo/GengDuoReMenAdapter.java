package com.bw.movie.apapter_gengduo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ReMenMovieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GengDuoReMenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<ReMenMovieBean.ResultBean> list;


    public GengDuoReMenAdapter(Context context, List<ReMenMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_gengduo_remen, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        String s = String.valueOf(list.get(i).getScore());
        ((ViewHolder) viewHolder).goupiaoReMenPing.setText(s+"人想看");
        ((ViewHolder) viewHolder).goupiaoReMenName.setText(list.get(i).getName());
        ((ViewHolder) viewHolder).goupiaoReMenDaoYan.setText("导演: "+list.get(i).getDirector());
        ((ViewHolder) viewHolder).goupiaoReMenZhu.setText("主演: "+list.get(i).getStarring()+"");
        Glide.with(context).load(list.get(i).getImageUrl()).into(((ViewHolder) viewHolder).goupiaoReMenImg);
        final int movieId = list.get(i).getMovieId();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goupiao_re_men_img)
        ImageView goupiaoReMenImg;
        @BindView(R.id.goupiao_re_men_name)
        TextView goupiaoReMenName;
        @BindView(R.id.goupiao_re_men_dao_yan)
        TextView goupiaoReMenDaoYan;
        @BindView(R.id.goupiao_re_men_zhu)
        TextView goupiaoReMenZhu;
        @BindView(R.id.goupiao_re_men_ping)
        TextView goupiaoReMenPing;
        @BindView(R.id.goupiao_re_men_gou_mai)
        TextView goupiaoReMenGouMai;
        @BindView(R.id.gengduo_ll)
        RelativeLayout gengduoLl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
