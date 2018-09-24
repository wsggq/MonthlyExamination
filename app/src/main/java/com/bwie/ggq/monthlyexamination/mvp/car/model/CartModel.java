package com.bwie.ggq.monthlyexamination.mvp.car.model;

import com.bwie.ggq.monthlyexamination.bean.CartsBean;
import com.bwie.ggq.monthlyexamination.utils.Retrofit_RXJava;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CartModel {
    public void getCart(String uid, final ICartModel iCartModel){
        Observable<CartsBean> observable = Retrofit_RXJava.getInstance().getInterface().getCarts(uid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iCartModel.onFailure("网络异常");
                    }

                    @Override
                    public void onNext(CartsBean cartsBean) {
                        iCartModel.onSuccess(cartsBean);
                    }
                });
    }
}
