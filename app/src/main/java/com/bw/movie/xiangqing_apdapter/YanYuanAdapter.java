package com.bw.movie.xiangqing_apdapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.XiangQingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 导演
 */
public class YanYuanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<XiangQingBean.ResultBean.MovieActorBean> movieDirector;


    public YanYuanAdapter(Context context, List<XiangQingBean.ResultBean.MovieActorBean> movieDirector) {
        this.context = context;
        this.movieDirector = movieDirector;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_director, null);
        ViewHolderXiangQingOne viewHolderXiangQingOne = new ViewHolderXiangQingOne(inflate);
        return viewHolderXiangQingOne;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolderXiangQingOne) viewHolder).tvName.setText(movieDirector.get(i).getName()+"");
        Uri uri = Uri.parse(movieDirector.get(i).getPhoto());
        ((ViewHolderXiangQingOne) viewHolder).iv.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return movieDirector.size();
    }

    public class ViewHolderXiangQingOne extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        public ViewHolderXiangQingOne(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
