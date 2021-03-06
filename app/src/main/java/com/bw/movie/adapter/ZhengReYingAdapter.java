package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ZhengShangYingBean;
import com.bw.movie.fragment.One;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ZhengReYingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<ZhengShangYingBean.ResultBean> list;

    public ZhengReYingAdapter(Context context, List<ZhengShangYingBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_releasemovie,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        Log.i("aaaaaa",list.get(i).getName());
        ((ViewHolder)viewHolder).tv.setText(String.valueOf(list.get(i).getScore()));
        ((ViewHolder)viewHolder).tv1.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getImageUrl()).into(((ViewHolder)viewHolder).iv);
        final int movieId = list.get(i).getMovieId();
        ((ViewHolder)viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                One.setXiangQingId(context,list.get(i).getMovieId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.gou_piao)
        TextView gou_piao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
