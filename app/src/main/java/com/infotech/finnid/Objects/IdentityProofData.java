package com.infotech.finnid.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdentityProofData {
    @SerializedName("identityName")
    @Expose
    private String identityName;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
