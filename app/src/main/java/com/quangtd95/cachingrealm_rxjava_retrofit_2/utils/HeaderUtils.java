package com.quangtd95.cachingrealm_rxjava_retrofit_2.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;

public class HeaderUtils {
    public static HashMap<String, String> buildHeaders() {
        HashMap<String, String> headers = new HashMap();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        return headers;
    }

    public static HashMap<String, String> buildHeaders(String author) {
        HashMap<String, String> headers = new HashMap();
        headers.put("Authorization", author);
        return headers;
    }

    public static HashMap<String, String> buildHeaders(String author, String type) {
        HashMap<String, String> headers = new HashMap();
        headers.put("Authorization", author);
        headers.put("Content-Type", type);
        return headers;
    }

    public static String formatCurrency(String input) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) format).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        decimalFormatSymbols.setGroupingSeparator('.');
        format.setMaximumFractionDigits(0);
        ((DecimalFormat) format).setDecimalFormatSymbols(decimalFormatSymbols);
        return format.format(Integer.valueOf(Integer.parseInt(input)));
    }

    public static void hideSoftKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }

    public static boolean isNullOrEmpty(String myString) {
        return myString == null || "".equals(myString);
    }
}
