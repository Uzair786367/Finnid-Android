package com.infotech.finnid.ApiRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberListRequest {
    @SerializedName("fromdate")
    @Expose
    public String fromdate;
    @SerializedName("todate")
    @Expose
    public String todate;
    @SerializedName("userName")
    @Expose
    public String userName;

    public MemberListRequest(String fromdate, String todate, String userName) {
        super();
        this.fromdate = fromdate;
        this.todate = todate;
        this.userName = userName;
    }

}
