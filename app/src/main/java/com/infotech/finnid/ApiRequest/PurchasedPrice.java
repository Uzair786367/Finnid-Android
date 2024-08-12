package com.infotech.finnid.ApiRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchasedPrice {

    @SerializedName("sn")
    @Expose
    private Integer sn;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("categoryText")
    @Expose
    private String categoryText;
    @SerializedName("taxableValue")
    @Expose
    private Integer taxableValue;
    @SerializedName("gst")
    @Expose
    private Integer gst;
    @SerializedName("cess")
    @Expose
    private Integer cess;
    @SerializedName("amount")
    @Expose
    private Integer amount;


    public PurchasedPrice(Integer sn, Integer category, String categoryText, Integer taxableValue, Integer gst, Integer cess, Integer amount) {
        this.sn = sn;
        this.category = category;
        this.categoryText = categoryText;
        this.taxableValue = taxableValue;
        this.gst = gst;
        this.cess = cess;
        this.amount = amount;
    }
}
