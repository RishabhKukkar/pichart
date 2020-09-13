package com.assignment.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.assignment.R;
import com.assignment.model.expenses.ExpensesResponse;
import com.assignment.model.expenses.Result;
import com.assignment.model.population.PopulationResponse;
import com.assignment.network.NetworkInterface;
import com.assignment.network.RetroFitClient;
import com.assignment.ui.base.BaseFragment;
import com.assignment.utils.AppUtils;
import com.assignment.utils.ConstantUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.assignment.utils.ConstantUtils.FRAGMENT_TYPE_EXPENSE;
import static com.assignment.utils.ConstantUtils.FRAGMENT_TYPE_POPULATION;
import static com.assignment.utils.ConstantUtils.INT_FIVE;
import static com.assignment.utils.ConstantUtils.INT_FOUR;
import static com.assignment.utils.ConstantUtils.INT_ONE;
import static com.assignment.utils.ConstantUtils.INT_ONE_THOUSAND;
import static com.assignment.utils.ConstantUtils.INT_THREE;
import static com.assignment.utils.ConstantUtils.INT_TWO;
import static com.assignment.utils.ConstantUtils.INT_ZERO;
import static com.assignment.utils.ConstantUtils.TEXT_EMPTY;
import static com.assignment.utils.ConstantUtils.TEXT_SIZE_ELEVEN;

public class PopulationAndExpenseFragment extends BaseFragment {
    //View
    private View view;

    @BindView(R.id.pieChartExpense)
    PieChart pieChartExpense;

    @BindView(R.id.barChartPopulation)
    BarChart barChartPopulation;

    //Strings
    private String fragmentType;

    //Integer
    private int expenseTotal;

    //Objects
    private NetworkInterface networkInterface;
    private PieDataSet pieDataSet;

    //ArrayList
    private List<Entry> pieChartEntries;
    private ArrayList<String> chartEntriesName;
    private ArrayList<BarEntry> barEntryArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentType = getArguments().getString(ConstantUtils.PD_FRAGMENT_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_population_and_expense, container, false);
        ButterKnife.bind(this, view);
        initModels();
        getData();
        return view;
    }

    private void getData() {
        switch (fragmentType) {
            case FRAGMENT_TYPE_POPULATION:
                getAndShowPopulationData();
                break;
            case FRAGMENT_TYPE_EXPENSE:
                getAndShowExpenseData();
                break;
        }
    }

    private void getAndShowExpenseData() {
        Observable<ExpensesResponse> expensesResponseObservable =
                networkInterface.getExpenseResponse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        expensesResponseObservable.subscribe(new Observer<ExpensesResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ExpensesResponse expensesResponse) {
                Result result = expensesResponse.getResults().get(INT_ZERO);
                expenseTotal = Integer.parseInt(result.getRent()) +
                        Integer.parseInt(result.getGrocery()) +
                        Integer.parseInt(result.getTransport()) +
                        Integer.parseInt(result.getCurrent()) +
                        Integer.parseInt(result.getSchoolFees()) +
                        Integer.parseInt(result.getSavings());

                pieChartEntries.add(new Entry(AppUtils.getValueInPercent(expenseTotal, Integer.parseInt(result.getRent())), INT_ZERO));
                pieChartEntries.add(new Entry(AppUtils.getValueInPercent(expenseTotal, Integer.parseInt(result.getGrocery())), INT_ONE));
                pieChartEntries.add(new Entry(AppUtils.getValueInPercent(expenseTotal, Integer.parseInt(result.getTransport())), INT_TWO));
                pieChartEntries.add(new Entry(AppUtils.getValueInPercent(expenseTotal, Integer.parseInt(result.getCurrent())), INT_THREE));
                pieChartEntries.add(new Entry(AppUtils.getValueInPercent(expenseTotal, Integer.parseInt(result.getSchoolFees())), INT_FOUR));
                pieChartEntries.add(new Entry(AppUtils.getValueInPercent(expenseTotal, Integer.parseInt(result.getSavings())), INT_FIVE));

                pieDataSet = new PieDataSet(pieChartEntries, TEXT_EMPTY);

                chartEntriesName.add(getString(R.string.rent));
                chartEntriesName.add(getString(R.string.grocery));
                chartEntriesName.add(getString(R.string.transport));
                chartEntriesName.add(getString(R.string.current));
                chartEntriesName.add(getString(R.string.schoolFees));
                chartEntriesName.add(getString(R.string.savings));


                pieDataSet.setColors(AppUtils.getColors(getContext()));
                PieData pieData = new PieData(chartEntriesName, pieDataSet);
                pieData.setValueTextSize(TEXT_SIZE_ELEVEN);
                pieData.setValueTextColor(Color.WHITE);
                pieChartExpense.setVisibility(View.VISIBLE);
                pieChartExpense.setData(pieData);
                pieChartExpense.setDescription(null);
                pieChartExpense.animateXY(INT_ONE_THOUSAND, INT_ONE_THOUSAND);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void getAndShowPopulationData() {
        Observable<PopulationResponse> populationResponseObservable =
                networkInterface.getPopulationResponse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        populationResponseObservable.subscribe(new Observer<PopulationResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(PopulationResponse populationResponse) {
                ArrayList<String> labelArrayList = new ArrayList<>();
                ArrayList<BarDataSet> barDataSetArrayList = new ArrayList<>();
                int i = INT_ZERO;
                for (com.assignment.model.population.Result result : populationResponse.getResults()) {
                    barEntryArrayList.add(new BarEntry(new float[]{Float.parseFloat(result.getMaleCount()), Float.parseFloat(result.getFemaleCount())}, i));
                    labelArrayList.add(result.getState());
                    i++;
                }
                BarDataSet set1 = new BarDataSet(barEntryArrayList, TEXT_EMPTY);
                set1.setColors(new int[]{
                        ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.darkPeach),
                        ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.darkRed)});
                barDataSetArrayList.add(set1);
                set1.setStackLabels(new String[]{getContext().getString(R.string.male), getContext().getString(R.string.female)});
                BarData data = new BarData(labelArrayList, barDataSetArrayList);
                data.setValueTextSize(TEXT_SIZE_ELEVEN);
                barChartPopulation.animateY(INT_ONE_THOUSAND);
                barChartPopulation.setData(data);
                barChartPopulation.invalidate();
                barChartPopulation.setDescription(null);
                barChartPopulation.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void initModels() {
        //Network
        networkInterface = RetroFitClient.getRetrofitInstance().create(NetworkInterface.class);

        //ArrayList
        pieChartEntries = new ArrayList<>();
        chartEntriesName = new ArrayList<>();
        barEntryArrayList = new ArrayList<>();
    }
}