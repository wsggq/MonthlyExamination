package com.bwie.ggq.monthlyexamination.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.bwie.ggq.monthlyexamination.R;
import com.bwie.ggq.monthlyexamination.bean.CartsBean;
import com.bwie.ggq.monthlyexamination.weight.MyJiaJianView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<CartsBean.DataBean.ListBean> listBeans;
    private CartCheckListener cartCheckListener;
    private CartAllCheckBoxListener cartAllCheckBoxListener;

    public ProductAdapter(Context context, List<CartsBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    public void setCartCheckListener(CartCheckListener cartCheckListener) {
        this.cartCheckListener = cartCheckListener;
    }

    public void setCartAllCheckBoxListener(CartAllCheckBoxListener cartAllCheckBoxListener) {
        this.cartAllCheckBoxListener = cartAllCheckBoxListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CartsBean.DataBean.ListBean listBean = listBeans.get(position);
        holder.productTitle.setText(listBean.getTitle());
        holder.productPrice.setText(listBean.getBargainPrice()+"");
        String[] split = listBean.getImages().split("\\|");
        if(split != null && split.length>0){
            holder.sdvProductLogo.setImageURI(split[0]);
        }else {
            holder.sdvProductLogo.setImageURI("https://img-blog.csdn.net/20151015101244652");
        }
        holder.productCheckbox.setChecked(listBean.isSelected());
        holder.jiajianView.setNumEt(listBean.getTotalNum());
        holder.jiajianView.setJiaJianListener(new MyJiaJianView.JiaJianListener() {
            @Override
            public void getNum(int num) {
                listBean.setNum(num);
                if(cartCheckListener != null){
                    cartCheckListener.notifyParent();//通知商家刷新适配器
                }
            }
        });
        holder.productCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.productCheckbox.isChecked()){
                    listBean.setSelected(true);
                }else {
                    listBean.setSelected(false);
                }
                if(cartCheckListener != null){
                    cartCheckListener.notifyParent();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size() == 0 ? 0 : listBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.product_checkbox)
        CheckBox productCheckbox;
        @BindView(R.id.sdv_productLogo)
        SimpleDraweeView sdvProductLogo;
        @BindView(R.id.product_title)
        TextView productTitle;
        @BindView(R.id.product_price)
        TextView productPrice;
        @BindView(R.id.jiajianView)
        MyJiaJianView jiajianView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
