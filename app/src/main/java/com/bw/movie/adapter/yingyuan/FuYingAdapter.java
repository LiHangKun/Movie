package com.bw.movie.adapter.yingyuan;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.FuYingBean;
import com.bw.movie.bean.TuiYingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuYingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<FuYingBean.ResultBean> result;

    public FuYingAdapter(Context context, List<FuYingBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_fuying, null);
        ViewHolderTuiYing viewHolderTuiYing = new ViewHolderTuiYing(inflate);
        return viewHolderTuiYing;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolderTuiYing) viewHolder).fuyingImg.setImageURI(Uri.parse(result.get(i).getLogo()));
        ((ViewHolderTuiYing) viewHolder).fuyingName.setText(result.get(i).getName() + "");
        ((ViewHolderTuiYing) viewHolder).fuyingXiang.setText(result.get(i).getAddress() + "");
        double b = Math.rint(result.get(i).getDistance()/100)/10;//这个结果是你要的千米
        ((ViewHolderTuiYing) viewHolder).fuyingJuli.setText(b+"km");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolderTuiYing extends RecyclerView.ViewHolder {
        @BindView(R.id.fuying_img)
        SimpleDraweeView fuyingImg;
        @BindView(R.id.fuying_name)
        TextView fuyingName;
        @BindView(R.id.fuying_xiang)
        TextView fuyingXiang;
        @BindView(R.id.fuying_juli)
        TextView fuyingJuli;
        public ViewHolderTuiYing(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
