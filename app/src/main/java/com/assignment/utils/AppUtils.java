package com.assignment.utils;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.assignment.R;

import java.util.ArrayList;

public class AppUtils {

    public static float getValueInPercent(int total, int value) {
        return (float) ((value * 100) / total);
    }

    public static ArrayList<Integer> getColors(Context context) {
        ArrayList<Integer> colorCodes = new ArrayList();
        colorCodes.add(ContextCompat.getColor(context, R.color.darkBlue));
        colorCodes.add(ContextCompat.getColor(context, R.color.darkOrange));
        colorCodes.add(ContextCompat.getColor(context, R.color.darkBrown));
        colorCodes.add(Color.BLACK);
        colorCodes.add(ContextCompat.getColor(context, R.color.darkRed));
        colorCodes.add(ContextCompat.getColor(context, R.color.darkPeach));
        return colorCodes;
    }
}
