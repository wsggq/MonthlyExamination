package com.bwie.ggq.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(findLayoutId());
        //绑定控件
        mUnbinder=ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void initData(){

    }

    protected  void initView(){

    }

    protected abstract int findLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=null) {
            mUnbinder.unbind();
            mUnbinder=null;
        }
    }
}
