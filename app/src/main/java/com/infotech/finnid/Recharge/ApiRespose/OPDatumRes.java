package com.infotech.finnid.Recharge.ApiRespose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OPDatumRes {

    @SerializedName("circleCode")
    @Expose
    private String circleCode;
    @SerializedName("circleName")
    @Expose
    private String circleName;
    @SerializedName("operatorType")
    @Expose
    private String operatorType;
    @SerializedName("operatorTypeId")
    @Expose
    private Integer operatorTypeId;
    @SerializedName("operatorName")
    @Expose
    private String operatorName;
    @SerializedName("spKey")
    @Expose
    private String spKey;
    @SerializedName("entryAt")
    @Expose
    private String entryAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public Integer getOperatorTypeId() {
        return operatorTypeId;
    }

    public void setOperatorTypeId(Integer operatorTypeId) {
        this.operatorTypeId = operatorTypeId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getSpKey() {
        return spKey;
    }

    public void setSpKey(String spKey) {
        this.spKey = spKey;
    }

    public String getEntryAt() {
        return entryAt;
    }

    public void setEntryAt(String entryAt) {
        this.entryAt = entryAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCircleCode() {
        return circleCode;
    }

    public void setCircleCode(String circleCode) {
        this.circleCode = circleCode;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }
}
