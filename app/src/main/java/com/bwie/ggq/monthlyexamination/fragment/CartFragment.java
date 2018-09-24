package com.bwie.ggq.monthlyexamination.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.bwie.ggq.base.BaseFragment;
import com.bwie.ggq.monthlyexamination.R;
import com.bwie.ggq.monthlyexamination.adapter.CartAdapter;
import com.bwie.ggq.monthlyexamination.adapter.CartAllCheckBoxListener;
import com.bwie.ggq.monthlyexamination.bean.CartsBean;
import com.bwie.ggq.monthlyexamination.mvp.car.presenter.CartPresenter;
import com.bwie.ggq.monthlyexamination.mvp.car.view.ICartView;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.internal.operators.flowable.FlowableFromIterable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment implements ICartView, View.OnClickListener,CartAllCheckBoxListener {
    private static final String TAG = "CartFragment";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.all_checkBox)
    CheckBox allCheckBox;
    @BindView(R.id.tv_totalPrice)
    TextView tvTotalPrice;
    Unbinder unbinder;
    private CartPresenter cartPresenter;
    private List<CartsBean.DataBean> data;
    private CartAdapter adapter;

    @Override
    protected int findLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cartPresenter = new CartPresenter(this);
        cartPresenter.getCart("17505");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allCheckBox.setOnClickListener(this);
    }

    /**
     * 购物车数据请求成功
     * @param cartsBean
     */
    @Override
    public void onSuccess(CartsBean cartsBean) {
        data = cartsBean.getData();
        if(cartsBean != null && data != null){
            adapter = new CartAdapter(getActivity(), data);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cartPresenter.onDestorys();
    }

    /**
     * 购物车全选按钮
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(allCheckBox.isChecked()){
            if(data != null && data.size()>0){
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setChecked(true);
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        data.get(i).getList().get(j).setSelected(true);
                    }
                }
            }
        }else {
            if(data != null && data.size()>0){
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setChecked(true);
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        data.get(i).getList().get(j).setSelected(false);
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
        totalPrice();
    }

    /**
     * 计算总价
     */
    private void totalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < adapter.getCartList().size(); i++) {
            for (int j = 0; j < adapter.getCartList().get(i).getList().size(); j++) {
                if(adapter.getCartList().get(i).getList().get(j).isSelected()){
                    CartsBean.DataBean.ListBean listBean = adapter.getCartList().get(i).getList().get(j);
                    totalPrice+=listBean.getBargainPrice()*listBean.getNum();
                }
            }
        }
        tvTotalPrice.setText("总价:¥"+totalPrice);
    }

    /**
     * 只要有一个没选中全选的CheckBox就不选
     */
    @Override
    public void notifyAllCheckbxoStatus() {
        StringBuffer stringBuffer = new StringBuffer();
        if(adapter != null){
            for (int i = 0; i < adapter.getCartList().size(); i++) {
                stringBuffer.append(adapter.getCartList().get(i).isChecked());
                for (int j = 0; j < adapter.getCartList().get(i).getList().size(); j++) {
                    if(adapter.getCartList().get(i).getList().get(j).isSelected()){
                        stringBuffer.append(true);
                    }
                }
            }
        }
        Log.d(TAG, "notifyAllCheckbxoStatus: "+stringBuffer);
        if(stringBuffer.toString().contains("false")){
            allCheckBox.setChecked(false);
        }else {
            allCheckBox.setChecked(true);
        }
        totalPrice();
    }
}
