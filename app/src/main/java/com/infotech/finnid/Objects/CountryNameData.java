package com.infotech.finnid.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryNameData {
    String itemId;
    String unit;
    String customerName;
    String businessName;
    Integer accountHeadId;
    Integer qty;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getAccountHeadId() {
        return accountHeadId;
    }

    public void setAccountHeadId(Integer accountHeadId) {
        this.accountHeadId = accountHeadId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bankInfo")
    @Expose
    private String bankInfo;

    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("id")
    @Expose
    private Integer id;


    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    //////////////////////////




    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("cessGst")
    @Expose
    private Integer cessGst;
    @SerializedName("gstRate")
    @Expose
    private Integer gstRate;
    @SerializedName("uomGroup")
    @Expose
    private Integer uomGroup;
    @SerializedName("standardUOM")
    @Expose
    private Integer standardUOM;
    @SerializedName("itemGroupCode")
    @Expose
    private Integer itemGroupCode;
    @SerializedName("barCode")
    @Expose
    private String barCode;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public Integer getCessGst() {
        return cessGst;
    }

    public void setCessGst(Integer cessGst) {
        this.cessGst = cessGst;
    }

    public Integer getGstRate() {
        return gstRate;
    }

    public void setGstRate(Integer gstRate) {
        this.gstRate = gstRate;
    }

    public Integer getUomGroup() {
        return uomGroup;
    }

    public void setUomGroup(Integer uomGroup) {
        this.uomGroup = uomGroup;
    }

    public Integer getStandardUOM() {
        return standardUOM;
    }

    public void setStandardUOM(Integer standardUOM) {
        this.standardUOM = standardUOM;
    }

    public Integer getItemGroupCode() {
        return itemGroupCode;
    }

    public void setItemGroupCode(Integer itemGroupCode) {
        this.itemGroupCode = itemGroupCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }


}
