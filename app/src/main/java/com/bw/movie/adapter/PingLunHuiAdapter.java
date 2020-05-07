package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.PingHui;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PingLunHuiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<PingHui.ResultBean> list;



    public PingLunHuiAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_ping_hui, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Uri string1 = Uri.parse(list.get(i).getReplyHeadPic());
        ((ViewHolder)viewHolder).yingPingImg.setImageURI(string1);
        ((ViewHolder)viewHolder).yingPingName.setText(list.get(i).getReplyUserName());
        ((ViewHolder)viewHolder).yingPingJieshao.setText(list.get(i).getReplyContent());
        String aformat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(aformat);
        String format = formatter.format(list.get(i).getReplyTime());
        ((ViewHolder)viewHolder).yingPingShijian.setText(format+"");


    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public void setData(List<PingHui.ResultBean> result) {
        list = result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ying_ping_img)
        SimpleDraweeView yingPingImg;
        @BindView(R.id.ying_ping_name)
        TextView yingPingName;
        @BindView(R.id.ying_ping_shijian)
        TextView yingPingShijian;
        @BindView(R.id.ying_ping_jieshao)
        TextView yingPingJieshao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
