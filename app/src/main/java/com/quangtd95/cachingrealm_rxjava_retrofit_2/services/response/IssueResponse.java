package com.quangtd95.cachingrealm_rxjava_retrofit_2.services.response;

import com.google.gson.annotations.SerializedName;

import io.realm.annotations.PrimaryKey;

/**
 * Quang_TD on 7/23/2017.
 */

public class IssueResponse {
    @SerializedName("url")
    private String url;
    @SerializedName("repository_url")
    private String repositoryUrl;
    @SerializedName("labels_url")
    private String labelUrl;
    @SerializedName("comments_url")
    private String commentUrl;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }

    public String getCommentUrl() {
        return commentUrl;
    }

    public void setCommentUrl(String commentUrl) {
        this.commentUrl = commentUrl;
    }

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
