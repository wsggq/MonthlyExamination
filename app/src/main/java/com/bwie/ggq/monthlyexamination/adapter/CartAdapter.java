package com.bwie.ggq.monthlyexamination.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.bwie.ggq.monthlyexamination.R;
import com.bwie.ggq.monthlyexamination.bean.CartsBean;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> implements CartCheckListener {
    private Context context;
    private List<CartsBean.DataBean> list;
    private CartAllCheckBoxListener cartAllCheckBoxListener;

    public CartAdapter(Context context, List<CartsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setCartAllCheckBoxListener(CartAllCheckBoxListener cartAllCheckBoxListener) {
        this.cartAllCheckBoxListener = cartAllCheckBoxListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layoout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CartsBean.DataBean dataBean = list.get(position);
        holder.tvSellerName.setText(dataBean.getSellerName());
        holder.sellerCheckBox.setChecked(dataBean.isChecked());
        holder.cartProductRv.setLayoutManager(new LinearLayoutManager(context));
        ProductAdapter adapter = new ProductAdapter(context, dataBean.getList());
        holder.cartProductRv.setAdapter(adapter);
        adapter.setCartCheckListener(this);
        for (int i = 0; i < dataBean.getList().size(); i++) {
            if(dataBean.getList().get(i).isSelected()){
                holder.sellerCheckBox.setChecked(true);
            }else {
                holder.sellerCheckBox.setChecked(false);
                break;
            }
        }
        //设置商家的CheckBox点击事件,逻辑:勾选则子条目全部勾选,取消则全部取消
        holder.sellerCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.sellerCheckBox.isChecked()){
                    dataBean.setChecked(true);
                    for (int i = 0; i < dataBean.getList().size(); i++) {
                        dataBean.getList().get(i).setSelected(true);
                    }
                }else {
                    dataBean.setChecked(false);
                    for (int i = 0; i < dataBean.getList().size(); i++) {
                        dataBean.getList().get(i).setSelected(false);
                    }
                }
                notifyDataSetChanged();
                if(cartAllCheckBoxListener != null){
                    cartAllCheckBoxListener.notifyAllCheckbxoStatus();
                }
            }
        });
    }

    /**
     * 暴露修改后的最新数据
     * @return
     */
    public List<CartsBean.DataBean> getCartList(){
        return list;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 商品传过来的刷新
     */
    @Override
    public void notifyParent() {
        notifyDataSetChanged();
        if(cartAllCheckBoxListener != null){
            cartAllCheckBoxListener.notifyAllCheckbxoStatus();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.sellerCheckBox)
        CheckBox sellerCheckBox;
        @BindView(R.id.tv_sellerName)
        TextView tvSellerName;
        @BindView(R.id.cart_productRv)
        RecyclerView cartProductRv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
