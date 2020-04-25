package com.bw.movie.xiangqing_apdapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.XiangQingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 导演
 */
public class YingPingTouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<?> replyHeadPic;

    public YingPingTouAdapter(Context context, List<?> replyHeadPic) {
        this.context = context;
        this.replyHeadPic = replyHeadPic;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_tou, null);
        ViewHolderXiangQingOne viewHolderXiangQingOne = new ViewHolderXiangQingOne(inflate);
        return viewHolderXiangQingOne;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        //Uri uri = Uri.parse(replyHeadPic.get);
        //((ViewHolderXiangQingOne) viewHolder).iv.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return replyHeadPic.size();
    }

    public class ViewHolderXiangQingOne extends RecyclerView.ViewHolder {
        @BindView(R.id.tou_img)
        SimpleDraweeView touImg;

        public ViewHolderXiangQingOne(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
