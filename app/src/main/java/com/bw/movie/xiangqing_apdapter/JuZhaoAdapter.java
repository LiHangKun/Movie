package com.bw.movie.xiangqing_apdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.XiangQingActivity;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.ZoomMediaLoader;
import com.previewlibrary.enitity.ThumbViewInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JuZhaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> list;


    private PhotoViewAttacher mAttacher;
    private View view;
    private Activity activity;
    private Dialog dialog;
    private ImageView imageView;
    private Dialog mLoadingDialog;
    private TextView tv;
    private ImageView iv;

    public JuZhaoAdapter(Context context, Activity activity, List<String> list) {
        this.context = context;
        this.list = list;
        this.activity=activity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.item_jizhao, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
       /* String s = list.get(position);
        Uri uri = Uri.parse(s);
        ((ViewHolder)holder).iv.setImageURI(uri);*/


        Glide.with(context).load(list.get(position)).into(((ViewHolder)holder).photoview);
        /*init();*/
        ((ViewHolder)holder).photoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    /*dialog.show();*/
                if (mLoadingDialog == null) {
                    mLoadingDialog = new Dialog(context);
                    if (mLoadingDialog.isShowing() == false) {
                        View view = View.inflate(context, R.layout.dialog_loading, null);
                        iv = view.findViewById(R.id.iv_loading);
                        tv = view.findViewById(R.id.tv);
                        Glide.with(context).load(list.get(position)).into(iv);
                        mLoadingDialog.addContentView(view,
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        mLoadingDialog.show();
                    }
                }else {
                    Dialog dialog = new Dialog(context);
                    if (dialog.isShowing() == false) {
                        View view = View.inflate(context, R.layout.dialog_loading, null);
                        iv = view.findViewById(R.id.iv_loading);
                        tv = view.findViewById(R.id.tv);
                        Glide.with(context).load(list.get(position)).into(iv);
                        dialog.addContentView(view,
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        dialog.show();
                    }
                }
            }
        });



    }

   /* private void init() {

        //展示在dialog上面的大图
        dialog = new Dialog(context, R.style.FullActivity);

        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(attributes);
        imageView = getImageView();

        dialog.setContentView(imageView);
        //大图的点击事件（点击让他消失）
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                dialog.dismiss();
            }
        });
    }*/
//动态的ImageView
/*
    private ImageView getImageView(){
        ImageView imageView = new ImageView(context);
        //宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //imageView设置图片
        @SuppressLint("ResourceType") InputStream is = context.getResources().openRawResource(R.mipmap.ic_launcher);
        Drawable drawable = BitmapDrawable.createFromStream(is, null);
        imageView.setImageDrawable(drawable);
        return imageView;
    }*/

    @Override
    public int getItemCount() {
        return list.size();
    }
    public OnClick mOnClick;
    public void setOnClickImg(OnClick onClickImg){
        mOnClick=onClickImg;
    }
    public interface OnClick{
        void onclick(int a);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photoview)
        ImageView photoview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
