package com.infotech.finnid.ApiRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemOfferDetail {

    @SerializedName("itemId")
    @Expose
    private Integer itemId;
    @SerializedName("offerItemId")
    @Expose
    private Integer offerItemId;
    @SerializedName("purchasedUOM")
    @Expose
    private Integer purchasedUOM;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("batchNumber")
    @Expose
    private String batchNumber;
    @SerializedName("expriryDate")
    @Expose
    private String expriryDate;


    public ItemOfferDetail(Integer itemId, Integer offerItemId, Integer purchasedUOM, Integer qty, String batchNumber, String expriryDate) {
        this.itemId = itemId;
        this.offerItemId = offerItemId;
        this.purchasedUOM = purchasedUOM;
        this.qty = qty;
        this.batchNumber = batchNumber;
        this.expriryDate = expriryDate;
    }
}
