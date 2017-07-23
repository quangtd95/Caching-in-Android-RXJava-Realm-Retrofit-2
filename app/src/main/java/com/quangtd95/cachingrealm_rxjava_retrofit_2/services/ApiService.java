package com.quangtd95.cachingrealm_rxjava_retrofit_2.services;

import com.quangtd95.cachingrealm_rxjava_retrofit_2.services.response.IssueResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * A interface uses to request API.
 *
 * @author QuangTD
 */
public interface ApiService {

    @GET("/repos/vmg/redcarpet/issues?state=closed")
    Observable<List<IssueResponse>> getIssueList();
}
