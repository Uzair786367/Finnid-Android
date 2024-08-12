package com.infotech.finnid.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.infotech.finnid.Objects.ShareTpeData;

import java.util.ArrayList;
import java.util.List;

public class ShareTypeResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<ShareTpeData> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ShareTpeData> getData() {
        return data;
    }

    public void setData(ArrayList<ShareTpeData> data) {
        this.data = data;
    }

}