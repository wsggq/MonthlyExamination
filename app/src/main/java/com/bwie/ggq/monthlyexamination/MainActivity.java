package com.bwie.ggq.monthlyexamination;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.ggq.monthlyexamination.fragment.CartFragment;
import com.bwie.ggq.monthlyexamination.fragment.ClassfiyFragment;
import com.bwie.ggq.monthlyexamination.fragment.FindFragment;
import com.bwie.ggq.monthlyexamination.fragment.IndexFragment;
import com.bwie.ggq.monthlyexamination.fragment.MyFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tabBar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager())
                .setFontSize(0)
                .setImgSize(160,160)
                .setChangeColor(Color.RED,Color.GRAY)
                .addTabItem("1",R.drawable.ac0,IndexFragment.class)
                .addTabItem("2",R.drawable.abw,ClassfiyFragment.class)
                .addTabItem("3",R.drawable.aby,FindFragment.class)
                .addTabItem("4",R.drawable.abu,CartFragment.class)
                .addTabItem("5",R.drawable.ac2,MyFragment.class);
    }
}
