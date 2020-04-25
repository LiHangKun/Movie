package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.apapter_gengduo.GengDuoComingSoonMovieAdapter;
import com.bw.movie.bean.JiangShangYingBean;
import com.bw.movie.fragment.One;

import org.greenrobot.eventbus.EventBus;

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
public class ComingSoonMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<JiangShangYingBean.ResultBean> list;


    public ComingSoonMovieAdapter(Context context, List<JiangShangYingBean.ResultBean> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_comingsoonmovie, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        Glide.with(context).load(list.get(i).getImageUrl()).into(((ViewHolder) viewHolder).iv);
        ((ViewHolder) viewHolder).tv.setText(list.get(i).getName());
        JiangShangYingBean.ResultBean resultBean = list.get(i);
        final int movieId = resultBean.getMovieId();
        ((ViewHolder) viewHolder).jijiangXiangkan.setText(list.get(i).getWantSeeNum()+"人想看");
        if(list.get(i).getWhetherReserve()==1){
            ((ViewHolder) viewHolder).yu_yue.setText("已预约");
        }else{
            ((ViewHolder) viewHolder).yu_yue.setText("预约");
        }
        //日期
        Date date = new Date(list.get(i).getReleaseTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        String[] split = format.split("-");
        ((ViewHolder)viewHolder).jijiangData.setText(split[1]+"月"+split[2]+"日"+"上映");

        ((ViewHolder) viewHolder).yu_yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                One.setClick(context,list.get(i).getMovieId());
            }
        });
        ((ViewHolder) viewHolder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                One.setXiangQingId(context,list.get(i).getMovieId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jijiang_data)
        TextView jijiangData;
        @BindView(R.id.jijiang_xiangkan)
        TextView jijiangXiangkan;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.rl)
        RelativeLayout rl;

        @BindView(R.id.yu_yue)
        TextView yu_yue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
