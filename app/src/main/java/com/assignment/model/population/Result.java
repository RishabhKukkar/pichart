
package com.assignment.model.population;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("male_count")
    @Expose
    private String maleCount;
    @SerializedName("female_count")
    @Expose
    private String femaleCount;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(String maleCount) {
        this.maleCount = maleCount;
    }

    public String getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(String femaleCount) {
        this.femaleCount = femaleCount;
    }

}
