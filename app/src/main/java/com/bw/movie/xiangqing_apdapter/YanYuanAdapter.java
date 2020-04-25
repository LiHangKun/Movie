package com.bw.movie.xiangqing_apdapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    @BindView(R.id.vp)
    ViewPager vp;
    private Dialog mLoadingDialog;
    private ImageView iv;
    private TextView tv;
    private String photo;
    private Bitmap bitmap;



    public YanYuanAdapter(Context context, List<XiangQingBean.ResultBean.MovieActorBean> movieDirector) {
        this.context = context;
        this.movieDirector = movieDirector;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View inflate = View.inflate(context, R.layout.item_director, null);
        ViewHolderXiangQingOne viewHolderXiangQingOne = new ViewHolderXiangQingOne(inflate);
        return viewHolderXiangQingOne;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolderXiangQingOne) viewHolder).tvName.setText(movieDirector.get(i).getName()+"");
        Uri uri = Uri.parse(movieDirector.get(i).getPhoto());
        ((ViewHolderXiangQingOne) viewHolder).iv.setImageURI(uri);
        photo = movieDirector.get(i).getPhoto();


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
