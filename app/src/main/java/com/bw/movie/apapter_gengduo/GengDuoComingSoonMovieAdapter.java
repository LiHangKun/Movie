package com.bw.movie.apapter_gengduo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.GengDuoActivity;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.fragment.One;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Week1_text
 * @Package: com.bw.text.adapter
 * @ClassName: ReMenAdapter
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/4/19 17:48
 */
public class GengDuoComingSoonMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<JiangShangYingBean.ResultBean> list;



    public GengDuoComingSoonMovieAdapter(Context context, List<JiangShangYingBean.ResultBean> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_gengduo_jijiang, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getImageUrl()).into(((ViewHolder) viewHolder).gengduoJijiangReImg);
        ((ViewHolder) viewHolder).gengduoJijiangName.setText(list.get(i).getName());
        JiangShangYingBean.ResultBean resultBean = list.get(i);
        final int movieId = resultBean.getMovieId();
        ((ViewHolder) viewHolder).gengduoJijiangXiangkan.setText(list.get(i).getWantSeeNum() + "");
        if (list.get(i).getWhetherReserve() == 1) {
            ((ViewHolder) viewHolder).gengReYuYue.setText("已预约");
        } else {
            ((ViewHolder) viewHolder).gengReYuYue.setText("预约");
        }
        //日期
        Date date = new Date(list.get(i).getReleaseTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        String[] split = format.split("-");
        ((ViewHolder)viewHolder).gengDuoJijiangRiqi.setText(split[1]+"月"+split[2]+"日"+"上映");

        ((ViewHolder) viewHolder).gengReYuYue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GengDuoActivity.setClick(context, list.get(i).getMovieId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gengduo_jijiang_re_img)
        ImageView gengduoJijiangReImg;
        @BindView(R.id.gengduo_jijiang_name)
        TextView gengduoJijiangName;
        @BindView(R.id.geng_duo_jijiang_riqi)
        TextView gengDuoJijiangRiqi;
        @BindView(R.id.gengduo_jijiang_xiangkan)
        TextView gengduoJijiangXiangkan;
        @BindView(R.id.gengduo_jijiang_yuyue)
        TextView gengReYuYue;
        @BindView(R.id.jijiang_ll)
        RelativeLayout jijiangLl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
