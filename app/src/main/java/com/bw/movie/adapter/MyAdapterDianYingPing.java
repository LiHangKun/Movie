package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.MyDianYingPingBean;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterDianYingPing extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MyDianYingPingBean.ResultBean> list;



    public MyAdapterDianYingPing(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_wo_dian_ying_left, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).getImageUrl()).into(((ViewHolder) viewHolder).iv);
        ((ViewHolder) viewHolder).tvMyfen.setText("("+list.get(i).getMyCommentScore()+"分)");
        ((ViewHolder) viewHolder).tvMypingjia.setText(list.get(i).getMyCommentContent());

        String aformat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(aformat);
        String format = formatter.format(list.get(i).getCommentTime());
        ((ViewHolder)viewHolder).tvMyshijian.setText(format+"");

        ((ViewHolder) viewHolder).tvDao.setText(list.get(i).getDirector()+"");
        ((ViewHolder) viewHolder).tvName.setText(list.get(i).getMovieName()+"");
        ((ViewHolder) viewHolder).tvZhu.setText(list.get(i).getStarring()+"");
        ((ViewHolder) viewHolder).tvPing.setText(list.get(i).getMovieScore()+"分");

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setData(List<MyDianYingPingBean.ResultBean> result) {
        list = result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_dao)
        TextView tvDao;
        @BindView(R.id.tv_zhu)
        TextView tvZhu;
        @BindView(R.id.tv_ping)
        TextView tvPing;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.tv_mypingjia)
        TextView tvMypingjia;
        @BindView(R.id.tv_myfen)
        TextView tvMyfen;
        @BindView(R.id.tv_myshijian)
        TextView tvMyshijian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
