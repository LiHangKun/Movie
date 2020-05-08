package com.bw.movie.adapter.yingyuan;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.DiBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiQuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    ArrayList<String> result;

    public DiQuAdapter(Context context, ArrayList<String> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_diqu, null);
        ViewHolderDiQu viewHolderDiQu = new ViewHolderDiQu(inflate);
        return viewHolderDiQu;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolderDiQu) viewHolder).tx.setText(result.get(i) + "");
        ((ViewHolderDiQu) viewHolder).dianDiqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnclick.click(i);
                notifyDataSetChanged();
            }
        });
       // 如果下标和传回来的下标相等 那么确定是点击的条目 把背景设置一下颜色
        if (i == getmPosition()) {
            ((ViewHolderDiQu) viewHolder).dianDiqu.setBackgroundColor(Color.parseColor("#141931"));
        }else{
//            否则的话就全白色初始化背景
            ((ViewHolderDiQu) viewHolder).dianDiqu.setBackgroundColor(Color.parseColor("#1A203F"));
        }
    }

    private  int mPosition=-1;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }




    public Onclick mOnclick;

    public void setOnCliCk(Onclick onCliCk) {
        mOnclick = onCliCk;
    }

    public interface Onclick {
        void click(int a);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolderDiQu extends RecyclerView.ViewHolder {
        @BindView(R.id.tx)
        TextView tx;
        @BindView(R.id.dian_diqu)
        LinearLayout dianDiqu;
        public ViewHolderDiQu(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
