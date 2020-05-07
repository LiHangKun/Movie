package com.bw.movie.adapter.yingyuan;

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
import com.bw.movie.bean.TuiYingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TuiYingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<TuiYingBean.ResultBean> result;


    public TuiYingAdapter(Context context, List<TuiYingBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_tuiying, null);
        ViewHolderTuiYing viewHolderTuiYing = new ViewHolderTuiYing(inflate);
        return viewHolderTuiYing;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        ((ViewHolderTuiYing)viewHolder).tuiyingImg.setImageURI(Uri.parse(result.get(i).getLogo()));
        ((ViewHolderTuiYing)viewHolder).tuiyingName.setText(result.get(i).getName()+"");
        ((ViewHolderTuiYing)viewHolder).tuiyingXiang.setText(result.get(i).getAddress()+"");
        ((ViewHolderTuiYing)viewHolder).tui_bei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickBei.onclick(result.get(i).getId());
            }
        });
    }
    OnClickBei mOnClickBei;
    public void setOnclickBei(OnClickBei onclickBei){
        mOnClickBei=onclickBei;
    }
    public interface OnClickBei{
        void onclick(int a);
    }
    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolderTuiYing extends RecyclerView.ViewHolder {
        @BindView(R.id.tuiying_img)
        SimpleDraweeView tuiyingImg;
        @BindView(R.id.tuiying_name)
        TextView tuiyingName;
        @BindView(R.id.tuiying_xiang)
        TextView tuiyingXiang;
        @BindView(R.id.tui_bei)
        RelativeLayout tui_bei;
        public ViewHolderTuiYing(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
