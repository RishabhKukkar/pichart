package com.assignment.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.assignment.R;
import com.assignment.ui.base.BaseApp;
import com.assignment.ui.fragments.HomeFragment;

import static com.assignment.utils.ConstantUtils.ADD_FRAGMENT;

public class MainActivity extends BaseApp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(getSupportFragmentManager(),
                R.id.fmlHomeContainer,
                new HomeFragment(),
                ADD_FRAGMENT,
                false,
                null
        );
    }
}