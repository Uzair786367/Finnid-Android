package com.infotech.finnid.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressProofData {
    @SerializedName("addresProofName")
    @Expose
    private String addresProofName;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getAddresProofName() {
        return addresProofName;
    }

    public void setAddresProofName(String addresProofName) {
        this.addresProofName = addresProofName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
