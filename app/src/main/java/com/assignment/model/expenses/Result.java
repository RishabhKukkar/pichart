
package com.assignment.model.expenses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("rent")
    @Expose
    private String rent;
    @SerializedName("grocery")
    @Expose
    private String grocery;
    @SerializedName("transport")
    @Expose
    private String transport;
    @SerializedName("current")
    @Expose
    private String current;
    @SerializedName("school_fees")
    @Expose
    private String schoolFees;
    @SerializedName("savings")
    @Expose
    private String savings;

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getGrocery() {
        return grocery;
    }

    public void setGrocery(String grocery) {
        this.grocery = grocery;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getSchoolFees() {
        return schoolFees;
    }

    public void setSchoolFees(String schoolFees) {
        this.schoolFees = schoolFees;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }

}
