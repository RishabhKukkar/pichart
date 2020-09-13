package com.assignment.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.assignment.R;
import com.assignment.ui.base.BaseFragment;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.assignment.utils.ConstantUtils.PD_FRAGMENT_TYPE;
import static com.assignment.utils.ConstantUtils.FRAGMENT_TYPE_EXPENSE;
import static com.assignment.utils.ConstantUtils.FRAGMENT_TYPE_POPULATION;
import static com.assignment.utils.ConstantUtils.REPLACE_FRAGMENT;

public class HomeFragment extends BaseFragment {
    //View Elements
    private View view;

    @BindView(R.id.btnShowBarChart)
    MaterialButton btnShowBarChart;

    @BindView(R.id.btnShowPieChart)
    MaterialButton btnShowPieChart;

    //Objects
    private PopulationAndExpenseFragment populationFragment;
    private PopulationAndExpenseFragment expenseFragment;

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
        initModels();
        setUpViewClickListener();
    }

    private void initModels() {
        populationFragment = new PopulationAndExpenseFragment();
        expenseFragment = new PopulationAndExpenseFragment();
    }

    private void setUpViewClickListener() {
        btnShowBarChart.setOnClickListener(v -> {
            populationFragment = new PopulationAndExpenseFragment();
            Bundle populationBundle = new Bundle();
            populationBundle.putString(PD_FRAGMENT_TYPE, FRAGMENT_TYPE_POPULATION);
            populationFragment.setArguments(populationBundle);
            changeFragment(getFragmentManager(),
                    R.id.fmlHomeContainer,
                    populationFragment,
                    REPLACE_FRAGMENT,
                    true,
                    null
            );
        });

        btnShowPieChart.setOnClickListener(v -> {
            expenseFragment = new PopulationAndExpenseFragment();
            Bundle expenseBundle = new Bundle();
            expenseBundle.putString(PD_FRAGMENT_TYPE, FRAGMENT_TYPE_EXPENSE);
            expenseFragment.setArguments(expenseBundle);
            changeFragment(getFragmentManager(),
                    R.id.fmlHomeContainer,
                    expenseFragment,
                    REPLACE_FRAGMENT,
                    true,
                    null
            );
        });
    }
}