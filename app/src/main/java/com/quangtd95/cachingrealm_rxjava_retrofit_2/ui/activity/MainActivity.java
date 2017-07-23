package com.quangtd95.cachingrealm_rxjava_retrofit_2.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.quangtd95.cachingrealm_rxjava_retrofit_2.R;
import com.quangtd95.cachingrealm_rxjava_retrofit_2.db.models.IssueRealm;
import com.quangtd95.cachingrealm_rxjava_retrofit_2.services.core.ApiClient;
import com.quangtd95.cachingrealm_rxjava_retrofit_2.services.response.IssueResponse;
import com.quangtd95.cachingrealm_rxjava_retrofit_2.ui.adapter.IssueAdapter;
import com.quangtd95.cachingrealm_rxjava_retrofit_2.utils.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRvIssue;
    private IssueAdapter issueAdapter;
    private List<IssueResponse> issueResponses;

    private Realm realmUI;

    private SwipeRefreshLayout srLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        fetchDataCaching();
    }

    private void initComponent() {
        realmUI = Realm.getDefaultInstance();
        mRvIssue = (RecyclerView) findViewById(R.id.rvIssue);
        issueResponses = new ArrayList<>();
        issueAdapter = new IssueAdapter(issueResponses);
        mRvIssue.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIssue.setAdapter(issueAdapter);
        srLayout = (SwipeRefreshLayout) findViewById(R.id.srLayout);
        srLayout.setOnRefreshListener(this::fetchDataWithoutCaching);

    }

    private void fetchDataCaching() {
        Observable<List<IssueResponse>> observable = ApiClient.getService().getIssueList();
        observable
                .delay(1L, java.util.concurrent.TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(this::writeToDB)
                .observeOn(Schedulers.computation())
                .map(this::readAllFromDB)
                .mergeWith(Observable.just(readAllFromDB(null)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::display, this::processError);
    }

    private void fetchDataWithoutCaching() {
        Observable<List<IssueResponse>> observable = ApiClient.getService().getIssueList();
        observable
                .delay(1L, java.util.concurrent.TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(this::writeToDB)
                .observeOn(Schedulers.computation())
                .map(this::readAllFromDB)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::display, this::processError);
    }


    private void processError(Throwable throwable) {
        Toast.makeText(this, "Check your network connection!!", Toast.LENGTH_SHORT).show();
        Log.e(throwable);
        if (srLayout.isRefreshing()) srLayout.setRefreshing(false);
    }

    private void display(List<IssueResponse> issueResponses) {
        if (srLayout.isRefreshing()) srLayout.setRefreshing(false);
        this.issueResponses.clear();
        for (int i = 0; i < issueResponses.size(); i++) {
            this.issueResponses.add(issueResponses.get(i));
        }
        issueAdapter.notifyDataSetChanged();
    }

    private IssueRealm findInDb(Realm realm, int id) {
        return realm.where(IssueRealm.class).equalTo("id", id).findFirst();
    }

    private List<IssueResponse> findAllInDB(Realm realm) {
        List<IssueRealm> issueRealms = realm.where(IssueRealm.class).findAll();
        List<IssueResponse> list = new ArrayList<>();
        for (int i = 0; i < issueRealms.size(); i++) {
            IssueResponse issueResponse = new IssueResponse();
            issueResponse.setId(issueRealms.get(i).getId());
            issueResponse.setTitle(issueRealms.get(i).getTitle());
            issueResponse.setBody(issueRealms.get(i).getBody());
            list.add(issueResponse);
        }
        return list;
    }

    private List<IssueResponse> readAllFromDB(List<IssueResponse> issueResponses) {
        return findAllInDB(Realm.getDefaultInstance());
    }

    private List<IssueResponse> writeToDB(List<IssueResponse> issueResponses) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm -> {
            for (int i = 0; i < issueResponses.size(); i++) {
                IssueResponse issueResponse = issueResponses.get(i);
                IssueRealm issueRealm = findInDb(transactionRealm, issueResponse.getId());
                if (issueRealm == null) {
                    issueRealm = transactionRealm.createObject(IssueRealm.class, issueResponse.getId());
                }
                issueRealm.setBody(issueResponse.getBody());
                issueRealm.setTitle(issueResponse.getTitle());
            }
        });
        realm.close();
        return issueResponses;

    }

    @Override protected void onDestroy() {
        super.onDestroy();
        realmUI.close();
    }
}
