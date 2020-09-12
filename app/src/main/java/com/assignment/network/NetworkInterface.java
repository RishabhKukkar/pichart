package com.assignment.network;

import com.assignment.model.expenses.ExpensesResponse;
import com.assignment.model.population.PopulationResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkInterface {

    @GET("assignment/api/getPopulationData.json")
    Observable<PopulationResponse> getPopulationResponse();

    @GET("assignment/api/getExpenses.json")
    Observable<ExpensesResponse> getExpenseResponse();
}
