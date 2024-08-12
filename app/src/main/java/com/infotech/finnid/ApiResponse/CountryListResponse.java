package com.infotech.finnid.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.infotech.finnid.Objects.CountryNameData;

import java.util.List;

public class CountryListResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<CountryNameData> data;

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

    public List<CountryNameData> getData() {
        return data;
    }

    public void setData(List<CountryNameData> data) {
        this.data = data;
    }

}
