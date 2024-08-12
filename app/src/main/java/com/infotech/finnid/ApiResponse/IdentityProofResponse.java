package com.infotech.finnid.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.infotech.finnid.Objects.IdentityProofData;

import java.util.List;

public class IdentityProofResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<IdentityProofData> data;

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

    public List<IdentityProofData> getData() {
        return data;
    }

    public void setData(List<IdentityProofData> data) {
        this.data = data;
    }
}

