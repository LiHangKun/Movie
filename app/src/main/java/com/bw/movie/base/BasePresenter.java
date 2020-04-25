package com.bw.movie.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView> implements BaseView {
    WeakReference<V> weakReference;
    public BasePresenter(V v){
        weakReference=new WeakReference<>(v);
        initModel();
    }

    protected abstract void initModel();
    public V getView(){
        if(weakReference!=null){
            return weakReference.get();
        }
        return null;
    }
    public void ondechView(){
        if(weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }


}
