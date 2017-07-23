package com.quangtd95.cachingrealm_rxjava_retrofit_2.db.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Quang_TD on 7/23/2017.
 */

public class IssueRealm extends RealmObject  {
    @PrimaryKey
    private int id;
    private String title;
    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
