package com.bwie.ggq.monthlyexamination.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.bwie.ggq.monthlyexamination.R;

public class MyJiaJianView extends LinearLayout implements View.OnClickListener {
    private int num = 1;
    private EditText etNum;
    private ImageView tvJian;
    private ImageView tvJia;

    public MyJiaJianView(Context context) {
        this(context, null);
    }

    public MyJiaJianView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyJiaJianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jia_jian_layoout, this, true);
        tvJia = view.findViewById(R.id.tv_jia);
        tvJian = view.findViewById(R.id.tv_jian);
        etNum = view.findViewById(R.id.et_num);
        etNum.setText(num+"");
        tvJia.setOnClickListener(this);
        tvJian.setOnClickListener(this);
    }

    public void setNumEt(int n){
        etNum.setText(n+"");
        num = Integer.parseInt(etNum.getText().toString());
    }
    private JiaJianListener jiaJianListener;

    public void setJiaJianListener(JiaJianListener jiaJianListener) {
        this.jiaJianListener = jiaJianListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_jia:
                num++;
                etNum.setText(num+"");
                if(jiaJianListener != null){
                    jiaJianListener.getNum(num);
                }
                break;
            case R.id.tv_jian:
                if(num==1){
                    Toast.makeText(getContext(), "数量不能小于1", Toast.LENGTH_SHORT).show();
                }else{
                    num--;
                }
                etNum.setText(num+"");
                if (jiaJianListener != null){
                    jiaJianListener.getNum(num);
                }
                break;
        }
    }

    public interface JiaJianListener{
        void getNum(int num);
    }
}
