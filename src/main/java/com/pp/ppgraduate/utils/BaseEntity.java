package com.pp.ppgraduate.utils;


public class BaseEntity extends PageModel {
    private String requestUserId;

    private String resubmitToken;

    public String getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(String requestUserId) {
        this.requestUserId = requestUserId;
    }

    public String getResubmitToken() {
        return resubmitToken;
    }

    public void setResubmitToken(String resubmitToken) {
        this.resubmitToken = resubmitToken;
    }
}
