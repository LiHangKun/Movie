package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.ChaYuYueBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YuYueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ChaYuYueBean.ResultBean> list;


    public YuYueAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_yuyue, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder)viewHolder).tvName.setText(list.get(i).getName());
        ((ViewHolder)viewHolder).tvCount.setText(list.get(i).getWantSeeNum()+"人想看");
        Date date = new Date(list.get(i).getReleaseTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        String[] split = format.split("-");
        ((ViewHolder)viewHolder).tvShijian.setText(split[1]+"月"+split[2]+"日"+"上映");
        ((ViewHolder)viewHolder).iv.setImageURI(Uri.parse(list.get(i).getImageUrl()));
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setData(List<ChaYuYueBean.ResultBean> result) {
        list = result;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_shijian)
        TextView tvShijian;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.rl)
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
