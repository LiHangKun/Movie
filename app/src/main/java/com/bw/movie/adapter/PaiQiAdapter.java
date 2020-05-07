package com.bw.movie.adapter;

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
import com.bw.movie.activity.PaiQiActivity;
import com.bw.movie.bean.GuanJianZiBean;
import com.bw.movie.bean.PaiQiBean;

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
public class PaiQiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    List<PaiQiBean.ResultBean> result;

    public PaiQiAdapter(Context context, List<PaiQiBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_paiqi, null);
        ViewHolderSearch viewHolder = new ViewHolderSearch(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ViewHolderSearch) viewHolder).searchName.setText(result.get(i).getName() + "");
        ((ViewHolderSearch) viewHolder).searchDaoyan.setText("导演" + result.get(i).getDirector() + "");
        ((ViewHolderSearch) viewHolder).searchZhuyan.setText("主演" + result.get(i).getStarring() + "");
        ((ViewHolderSearch) viewHolder).searchPingfen.setText("评分" + result.get(i).getScore() + "");
        Glide.with(context).load(result.get(i).getImageUrl()).into(((ViewHolderSearch)viewHolder).searchImg);
    }

    @Override
    public int getItemCount() {
        if(result!=null){
            return result.size();
        }
        return 0;
    }

    public class ViewHolderSearch extends RecyclerView.ViewHolder {
        @BindView(R.id.search_img)
        ImageView searchImg;
        @BindView(R.id.search_name)
        TextView searchName;
        @BindView(R.id.search_daoyan)
        TextView searchDaoyan;
        @BindView(R.id.search_zhuyan)
        TextView searchZhuyan;
        @BindView(R.id.search_pingfen)
        TextView searchPingfen;
        @BindView(R.id.rl)
        RelativeLayout rl;

        public ViewHolderSearch(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
