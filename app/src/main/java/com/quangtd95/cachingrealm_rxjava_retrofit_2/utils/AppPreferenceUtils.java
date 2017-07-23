package com.quangtd95.cachingrealm_rxjava_retrofit_2.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreferenceUtils {
    private static AppPreferenceUtils mAppPreferenceUtils;
    private SharedPreferences mSharedPreferences;

    public static AppPreferenceUtils getInstance(Context context) {
        if (mAppPreferenceUtils == null) {
            mAppPreferenceUtils = new AppPreferenceUtils();
            mAppPreferenceUtils.mSharedPreferences = context.getSharedPreferences("caching", 0);
        }
        return mAppPreferenceUtils;
    }

    public void putString(String key, String value) {
        Editor editor = this.mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        Editor editor = this.mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {
        Editor editor = this.mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getString(String key, String defaults) {
        return this.mSharedPreferences.getString(key, defaults);
    }

    public int getInt(String key, int defaults) {
        return this.mSharedPreferences.getInt(key, defaults);
    }

    public boolean getBoolean(String key, boolean defaults) {
        return this.mSharedPreferences.getBoolean(key, defaults);
    }
}
