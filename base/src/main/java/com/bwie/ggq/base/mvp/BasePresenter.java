package com.bwie.ggq.base.mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M,V>{
    public M mModel;
    public V mView;
    private WeakReference<V> mWeakReference;

   public abstract M getmModel();

    public void attach(M mModel ,V mView){
        this.mModel=mModel;
        mWeakReference = new WeakReference<>(mView);
        this.mView=mWeakReference.get();
    }

    //解绑
    public void detach(){
        if (mWeakReference!=null) {
            mWeakReference.clear();;
            mWeakReference=null;
        }
    }
}
