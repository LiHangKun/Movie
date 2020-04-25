package com.bw.movie.xiangqing_apdapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;


import com.bw.movie.R;
import com.bw.movie.bean.XiangQingBean;

import java.util.List;

/**
 * @ClassName VideoAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/4/240:52
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<XiangQingBean.ResultBean.ShortFilmListBean> list;

    public VideoAdapter(Context context, List<XiangQingBean.ResultBean.ShortFilmListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_video, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getVideoUrl());
        ((ViewHolder)holder).vv.setVideoURI(uri);
        ((ViewHolder)holder).tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewHolder)holder).vv.start();
                ((ViewHolder)holder).tv.setVisibility(8);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final VideoView vv;
        private final TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vv = itemView.findViewById(R.id.vv);
            tv = itemView.findViewById(R.id.bu);
        }
    }
}
