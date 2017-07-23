package com.quangtd95.cachingrealm_rxjava_retrofit_2;

import android.app.Application;

import com.quangtd95.cachingrealm_rxjava_retrofit_2.services.core.ApiClient;
import com.quangtd95.cachingrealm_rxjava_retrofit_2.services.core.ApiConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by oanhdao on 4/25/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        createService();
        createDatabase();

    }

    private void createDatabase() {
        Realm.init(this);
    }

    private void createService() {
        ApiConfig apiConfig = new ApiConfig(this, getString(R.string.url_base));
        ApiClient.getInstance().init(apiConfig);
    }
}
