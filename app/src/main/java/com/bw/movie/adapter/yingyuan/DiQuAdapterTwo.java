package com.bw.movie.adapter.yingyuan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.DiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiQuAdapterTwo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DiBean.ResultBean> result;



    public DiQuAdapterTwo(Context context, List<DiBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_diqu_two, null);
        ViewHolderDiQu viewHolderDiQu = new ViewHolderDiQu(inflate);
        return viewHolderDiQu;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolderDiQu) viewHolder).txDiquTwo.setText(result.get(i).getName() + "");

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
        if(result!=null){
            return result.size();
        }
        return 0;
    }

    public class ViewHolderDiQu extends RecyclerView.ViewHolder {
        @BindView(R.id.tx_diqu_two)
        TextView txDiquTwo;
        @BindView(R.id.dian_diqu_two)
        LinearLayout dianDiquTwo;
        public ViewHolderDiQu(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
