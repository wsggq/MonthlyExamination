package com.bwie.ggq.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  LayoutInflater.from(getContext()).inflate(findLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder=ButterKnife.bind(this,view);
        initView();
        initData();
    }

    protected void initData() {
    }

    protected void initView() {

    }

    protected abstract int findLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=null) {
            mUnbinder.unbind();
            mUnbinder=null;
        }
    }
}

