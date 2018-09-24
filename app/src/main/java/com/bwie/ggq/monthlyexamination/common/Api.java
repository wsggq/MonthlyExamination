package com.bwie.ggq.monthlyexamination.common;

import com.bwie.ggq.monthlyexamination.bean.CartsBean;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {
    //https://www.zhaoapi.cn/product/getCarts?uid=17505
    @POST("product/getCarts")
    Observable<CartsBean> getCarts(@Query("uid") String uid);
}
