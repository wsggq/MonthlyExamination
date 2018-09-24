package com.bwie.ggq.monthlyexamination.mvp.car.presenter;

import android.util.Log;
import com.bwie.ggq.monthlyexamination.bean.CartsBean;
import com.bwie.ggq.monthlyexamination.mvp.car.model.CartModel;
import com.bwie.ggq.monthlyexamination.mvp.car.model.ICartModel;
import com.bwie.ggq.monthlyexamination.mvp.car.view.ICartView;

public class CartPresenter implements ICartPresenter{
    private static final String TAG = "CartPresenter";
    private ICartView iCartView;
    private CartModel cartModel;

    public CartPresenter(ICartView iCartView) {
        this.iCartView = iCartView;
        this.cartModel = new CartModel();
    }

    public void getCart(String uid) {
        cartModel.getCart(uid, new ICartModel() {
            @Override
            public void onSuccess(CartsBean cartsBean) {
                iCartView.onSuccess(cartsBean);
            }

            @Override
            public void onFailure(String msg) {
                Log.d(TAG, "onFailure: "+msg);
            }
        });
    }

    @Override
    public void onDestorys() {
        if(iCartView != null){
            iCartView = null;
        }
    }
}
