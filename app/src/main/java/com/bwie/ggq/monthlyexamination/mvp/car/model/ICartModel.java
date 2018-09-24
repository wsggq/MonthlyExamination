package com.bwie.ggq.monthlyexamination.mvp.car.model;

import com.bwie.ggq.monthlyexamination.bean.CartsBean;

public interface ICartModel {
    void onSuccess(CartsBean cartsBean);
    void onFailure(String msg);
}
