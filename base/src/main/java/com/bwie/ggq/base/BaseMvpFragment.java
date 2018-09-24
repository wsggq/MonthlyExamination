package com.bwie.ggq.base;

import com.bwie.ggq.base.mvp.BasePresenter;
import com.bwie.ggq.base.mvp.IBaseModel;
import com.bwie.ggq.base.mvp.IBaseView;

public abstract class BaseMvpFragment<M extends IBaseModel,P extends BasePresenter> extends BaseFragment implements IBaseView {
    public M model;
    public P presenter;
    @Override
    protected void initData() {
        super.initData();
         presenter = (P) initBasePresenter();
        if (presenter!=null) {
            model = (M) presenter.getmModel();
            if (model!=null) {
                presenter.attach(model,this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null) {
            presenter.detach();
        }
    }
}
