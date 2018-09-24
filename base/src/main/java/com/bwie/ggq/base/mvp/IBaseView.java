package com.bwie.ggq.base.mvp;

public interface IBaseView {
    BasePresenter initBasePresenter();
    void showRetrofit();
    void hideRetrofit();
    void failure(String msg);
}
