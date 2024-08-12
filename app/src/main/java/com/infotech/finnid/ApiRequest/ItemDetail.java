package com.infotech.finnid.ApiRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDetail {


    @SerializedName("sn")
    @Expose
    private Integer sn;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("batchNumber")
    @Expose
    private String batchNumber;
    @SerializedName("expriryDate")
    @Expose
    private String expriryDate;
    @SerializedName("purchasedUOM")
    @Expose
    private Integer purchasedUOM;
    @SerializedName("standardUOM")
    @Expose
    private Integer standardUOM;
    @SerializedName("paidItemQty")
    @Expose
    private Integer paidItemQty;
    @SerializedName("freeItemQty")
    @Expose
    private Integer freeItemQty;
    @SerializedName("totalItemQty")
    @Expose
    private Integer totalItemQty;
    @SerializedName("cgstPerUnit")
    @Expose
    private Integer cgstPerUnit;
    @SerializedName("sgstPerUnit")
    @Expose
    private Integer sgstPerUnit;
    @SerializedName("isgtPerUnit")
    @Expose
    private Integer isgtPerUnit;
    @SerializedName("cessPerUnit")
    @Expose
    private Integer cessPerUnit;
    @SerializedName("isUnderOfferItem")
    @Expose
    private Boolean isUnderOfferItem;
    @SerializedName("voucherItemSequnceId")
    @Expose
    private Integer voucherItemSequnceId;
    @SerializedName("mrp")
    @Expose
    private Integer mrp;
    @SerializedName("hsn")
    @Expose
    private String hsn;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("cessRate")
    @Expose
    private Double cessRate;
    @SerializedName("gstRate")
    @Expose
    private Double gstRate;
    @SerializedName("transactionValue")
    @Expose
    private Double transactionValue;
    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;


    public ItemDetail(Integer sn, String itemCode, String batchNumber, String expriryDate, Integer purchasedUOM,
                      Integer standardUOM, Integer paidItemQty, Integer freeItemQty, Integer totalItemQty,
                      Integer cgstPerUnit, Integer sgstPerUnit, Integer isgtPerUnit, Integer cessPerUnit,
                      Boolean isUnderOfferItem, Integer voucherItemSequnceId, Integer mrp, String hsn,
                      Integer rate, Double cessRate, Double gstRate, Double transactionValue, Double totalAmount) {
        this.sn = sn;
        this.itemCode = itemCode;
        this.batchNumber = batchNumber;
        this.expriryDate = expriryDate;
        this.purchasedUOM = purchasedUOM;
        this.standardUOM = standardUOM;
        this.paidItemQty = paidItemQty;
        this.freeItemQty = freeItemQty;
        this.totalItemQty = totalItemQty;
        this.cgstPerUnit = cgstPerUnit;
        this.sgstPerUnit = sgstPerUnit;
        this.isgtPerUnit = isgtPerUnit;
        this.cessPerUnit = cessPerUnit;
        this.isUnderOfferItem = isUnderOfferItem;
        this.voucherItemSequnceId = voucherItemSequnceId;
        this.mrp = mrp;
        this.hsn = hsn;
        this.rate = rate;
        this.cessRate = cessRate;
        this.gstRate = gstRate;
        this.transactionValue = transactionValue;
        this.totalAmount = totalAmount;
    }
}
