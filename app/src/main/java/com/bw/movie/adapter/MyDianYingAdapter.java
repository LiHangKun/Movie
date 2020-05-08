package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.MyGuanDianyingBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDianYingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MyGuanDianyingBean.ResultBean> list;


    public MyDianYingAdapter(Context context) {
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
        ((ViewHolder)viewHolder).searchName.setText(list.get(i).getName()+"");
        ((ViewHolder)viewHolder).searchDaoyan.setText(list.get(i).getDirector()+"");
        ((ViewHolder)viewHolder).searchPingfen.setText(list.get(i).getScore()+"分");
        ((ViewHolder)viewHolder).searchZhuyan.setText(list.get(i).getStarring()+"");
        Glide.with(context).load(list.get(i).getImageUrl()).into(((ViewHolder)viewHolder).searchImg);

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setData(List<MyGuanDianyingBean.ResultBean> result) {
        list = result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_img)
        ImageView searchImg;
        @BindView(R.id.search_name)
        TextView searchName;
        @BindView(R.id.search_daoyan)
        TextView searchDaoyan;
        @BindView(R.id.search_zhuyan)
        TextView searchZhuyan;
        @BindView(R.id.search_pingfen)
        TextView searchPingfen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
