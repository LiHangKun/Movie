package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.GuanYingYuanBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyGuanYingYuanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<GuanYingYuanBean.ResultBean> list;

    public MyGuanYingYuanAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_my_guan_ying_yuan, null);
        ViewHolderTuiYing viewHolderTuiYing = new ViewHolderTuiYing(inflate);
        return viewHolderTuiYing;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolderTuiYing)viewHolder).fuyingName.setText(list.get(i).getName());
        ((ViewHolderTuiYing)viewHolder).fuyingXiang.setText(list.get(i).getAddress()+"");
        Uri parse = Uri.parse(list.get(i).getLogo());
        ((ViewHolderTuiYing)viewHolder).fuyingImg.setImageURI(parse);

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public void setData(List<GuanYingYuanBean.ResultBean> result) {
        list=result;
        notifyDataSetChanged();
    }

    public class ViewHolderTuiYing extends RecyclerView.ViewHolder {
        @BindView(R.id.fuying_img)
        SimpleDraweeView fuyingImg;
        @BindView(R.id.fuying_name)
        TextView fuyingName;
        @BindView(R.id.fuying_xiang)
        TextView fuyingXiang;

        public ViewHolderTuiYing(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
