package com.infotech.finnid.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FieldUOMData {
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
