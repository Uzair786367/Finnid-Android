package com.infotech.finnid.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareTpeData {
    @SerializedName("shareDetail")
    @Expose
    private String shareDetail;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getShareDetail() {
        return shareDetail;
    }

    public void setShareDetail(String shareDetail) {
        this.shareDetail = shareDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
