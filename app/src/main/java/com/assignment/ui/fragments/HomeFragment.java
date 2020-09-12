package com.assignment.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.R;
import com.assignment.ui.base.BaseFragment;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.assignment.utils.ConstantUtils.ADD_FRAGMENT;

public class HomeFragment extends BaseFragment {
    //View Elements
    private View view;

    @BindView(R.id.btnShowBarChart)
    MaterialButton btnShowBarChart;

    @BindView(R.id.btnShowPieChart)
    MaterialButton btnShowPieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewClickListener();
    }

    private void setUpViewClickListener() {
        btnShowBarChart.setOnClickListener(v -> {
            changeFragment(getFragmentManager(),
                    R.id.fmlHomeContainer,
                    new PopulationAndExpenseFragment(),
                    ADD_FRAGMENT,
                    true,
                    null
            );
        });

        btnShowPieChart.setOnClickListener(v -> {
            changeFragment(getFragmentManager(),
                    R.id.fmlHomeContainer,
                    new PopulationAndExpenseFragment(),
                    ADD_FRAGMENT,
                    true,
                    null
            );
        });
    }
}