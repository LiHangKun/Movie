package com.bw.movie.adapter.yingyuan;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.YingYuanPingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YingYuanPingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<YingYuanPingBean.ResultBean> list;


    public YingYuanPingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_ying_yuan_ping, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).yingPingName.setText(list.get(i).getCommentUserName() + "");
        ((ViewHolder) viewHolder).yingPingJieshao.setText(list.get(i).getCommentContent() + "");
        String aformat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(aformat);
        String format = formatter.format(list.get(i).getCommentTime());
        ((ViewHolder) viewHolder).yingPingShijian.setText(format + "");
        Uri string1 = Uri.parse(list.get(i).getCommentHeadPic());
        ((ViewHolder) viewHolder).yingPingImg.setImageURI(string1);
        if (list.get(i).getIsGreat() == 0) {
            ((ViewHolder) viewHolder).zan.setImageResource(R.mipmap.weizan);
        }else{
            ((ViewHolder) viewHolder).zan.setImageResource(R.mipmap.yizan);
        }
        ((ViewHolder)viewHolder).zanRenshu.setText(list.get(i).getGreatNum()+"人觉得很赞");
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public void setData(List<YingYuanPingBean.ResultBean> result) {
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
        @BindView(R.id.zan)
        ImageView zan;
        @BindView(R.id.zan_renshu)
        TextView zanRenshu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
